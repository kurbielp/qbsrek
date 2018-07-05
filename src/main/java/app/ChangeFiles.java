package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ChangeFiles {


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String result="";

    public String getIfResult() {
        return ifResult;
    }

    public void setIfResult(String ifResult) {
        this.ifResult = ifResult;
    }

    private String ifResult="";

    public int getCounterReplace() {
        return counterReplace;
    }

    public void setCounterReplace(int counterReplace) {
        this.counterReplace = counterReplace;
    }

    private int counterReplace = 0;

    public ArrayList<Integer> getReplacementPlace() {
        return replacementPlace;
    }

    public void setReplacementPlace(ArrayList<Integer> replacementPlace) {
        this.replacementPlace = replacementPlace;
    }

    ArrayList<Integer> replacementPlace = new ArrayList<Integer>();

    public int getIfFileNumber() {
        return ifFileNumber;
    }

    public void setIfFileNumber(int ifFileNumber) {
        this.ifFileNumber = ifFileNumber;
    }

    private int ifFileNumber=0;

    public void ifListf(String directoryPath, List<File> files , String fileExtension, byte[] patternOld ){


        File directory = new File(directoryPath);
        //System.out.println(directoryPath);
        try {

            File[] fList = directory.listFiles();
            for (File file : fList) {
                if (file.isFile() && fileExtension.equals(FilenameUtils.getExtension(file.getName())) ) {


                    System.out.println("ZNALAZlem plik");
                    //System.out.println();
                    try{

                        //byte[] data = readBytesFromFile(file.getAbsolutePath());
                        byte[] data = FileUtils.readFileToByteArray(file);
                        replacementPlace.clear();
                        naiveAlgoritm(data,patternOld);
                        System.out.println(replacementPlace.size());
                        //naiveAlgoritm(data,patternOld);
                        if(getReplacementPlace().size()>0) {
                            setIfFileNumber(getIfFileNumber()+1);
                            setIfResult(ifResult + file.getAbsolutePath() + "\n");
                            System.out.println(getIfResult());
                        }

                    }catch (FileNotFoundException ef){
                        System.out.println(ef);
                    }

                } else if (file.isDirectory()) {
                    ifListf(file.getAbsolutePath(), files , fileExtension,patternOld );
                }
            }
        }catch (Exception e){
            System.out.println( "[qbsrek] ChangesFiles.listf " + "[error] "+ e);
        }

    }

    public void listf(String directoryPath, List<File> files , String fileExtension, byte[] patternOld,byte[] patternNew ){


        File directory = new File(directoryPath);
        //System.out.println(directoryPath);
        try {

            File[] fList = directory.listFiles();
            for (File file : fList) {
                if (file.isFile() && fileExtension.equals(FilenameUtils.getExtension(file.getName())) ) {


                        System.out.println("ZNALAZlem plik");
                        //System.out.println();
                        try{

                            //byte[] data = readBytesFromFile(file.getAbsolutePath());
                            byte[] data = FileUtils.readFileToByteArray(file);
                            replacementPlace.clear();
                            naiveAlgoritm(data,patternOld);
                            byte [] outputBytes = new byte[data.length + (patternNew.length-patternOld.length)*replacementPlace.size()];
                            System.out.println(replacementPlace.size());
                            //naiveAlgoritm(data,patternOld);
                            try {
                                outputBytes = replaceBytes(data, patternOld, patternNew);
                            }catch (Exception eReplace){
                                System.out.println("[qbsrek] Bytes replacement: " + eReplace);
                            }
                            if(getReplacementPlace().size()>0)
                                setResult(result + "W pliku: "+ file.getAbsolutePath()+ " zamieniono " + getCounterReplace() + " ciągów \n");
                            System.out.println(result);

                            FileUtils.writeByteArrayToFile(file, outputBytes);

                        }catch (FileNotFoundException ef){
                            System.out.println(ef);
                        }

                } else if (file.isDirectory()) {
                    listf(file.getAbsolutePath(), files , fileExtension,patternOld ,patternNew);
               }
            }
        }catch (Exception e){
            System.out.println( "[qbsrek] ChangesFiles.listf " + "[error] "+ e);
        }

    }

    public void naiveAlgoritm (byte[] data, byte[] patternOld){
        int n = data.length;
        int m = patternOld.length;

        int j;
        int i=0;
        while (i<=n-m)
        {
            j=0;
            while ((j<m) && (patternOld[j] == data[i+j])) j++;
            if (j==m)
                replacementPlace.add(i);
            i++;
        }
    }
    public byte[] replaceBytes( byte[] data, byte[] patternOld, byte[] patternNew)
    {

        if (replacementPlace.size()==0)
        {
            byte[] result = data;
            setCounterReplace(0);
            return result;
        }
        else
        {
            int tempDifference = (patternNew.length - patternOld.length);
            byte[] result = new byte[data.length + tempDifference * replacementPlace.size()];
            int tempDestPos=0;
            int tempLength=0;
            int tempSourcePos=0;
            System.out.println(patternNew.toString());
            System.arraycopy(data, 0, result, 0, replacementPlace.get(0) );
            for (int i = 0; i < replacementPlace.size(); i++) {
                if(replacementPlace.get(i)+tempDifference*i+patternNew.length-1<result.length)
                    System.arraycopy(patternNew, 0, result , replacementPlace.get(i) + tempDifference*(i) , patternNew.length);
                tempSourcePos=replacementPlace.get(i)+patternOld.length;
                tempDestPos = replacementPlace.get(i) + (tempDifference)*(i) + patternNew.length ;
                if (i+1<replacementPlace.size())
                    tempLength = replacementPlace.get(i+1) -  replacementPlace.get(i)-patternOld.length;
                else
                    tempLength = result.length -  (replacementPlace.get(i)+patternNew.length+ tempDifference*i);
                //tempLength = replacementPlace.get(i+1) -  replacementPlace.get(i);
                if(tempDestPos<result.length && tempLength>0)
                    System.arraycopy(data, tempSourcePos, result, tempDestPos , tempLength );

            }
            setCounterReplace(replacementPlace.size());
            System.out.println("RESULT " + result.toString());
            return result;
        }
    }

/*
    private static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }
*/
}
