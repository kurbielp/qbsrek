import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public int getCounterReplace() {
        return counterReplace;
    }

    public void setCounterReplace(int counterReplace) {
        this.counterReplace = counterReplace;
    }

    private int counterReplace = 0;

    public void listf(String directoryPath, List<File> files , String fileExtension, String patternOld,String patternNew ){


        File directory = new File(directoryPath);
        //System.out.println(directoryPath);
        try {

            File[] fList = directory.listFiles();
            for (File file : fList) {
                if (file.isFile() && fileExtension.equals(FilenameUtils.getExtension(file.getName())) ) {

                    if(FilenameUtils.getExtension(file.getName()).equals("txt")){
                        System.out.println("ZNALAZlem txtka");
                        //System.out.println();
                        try{

                            Path path = Paths.get(file.getAbsolutePath());
                            byte[] data =

                        while (scanner.hasNextLine()){
                            stringBuilder.append(scanner.nextLine() +"\n" );
                        }

                        try {
                            if (stringBuilder.length()!=0)
                                stringBuilder.deleteCharAt(stringBuilder.length()-1);
                        } catch (Exception eDeleteCharAt){System.out.println(eDeleteCharAt);}


                            replaceAll(stringBuilder,patternOld,patternNew);
                        setResult(result + "W pliku: "+ file.getAbsolutePath()+ " zamieniono " + getCounterReplace() + " ciągów \n");
                        System.out.println(result);
                        text = stringBuilder.toString();
                        System.out.println(stringBuilder.toString());
                        FileUtils.writeStringToFile(file, text);

                        }catch (FileNotFoundException ef){
                            System.out.println(ef);
                        }
                    }
                } else if (file.isDirectory()) {
                    listf(file.getAbsolutePath(), files , fileExtension,patternOld ,patternNew);
               }
            }
        }catch (Exception e){
            System.out.println( "[qbsrek] ChangesFiles.listf " + "[error] "+ e);
        }

    }

    public void replaceAll(StringBuilder builder, String from, String to )
    {
        int counter = 0;
        int index = builder.indexOf(from);
        while (index != -1)
        {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
            counter++;
        }
        this.setCounterReplace(counter);
    }

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

}
