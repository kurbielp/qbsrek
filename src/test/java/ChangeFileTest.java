import app.ChangeFiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ChangeFileTest {



    ArrayList<Integer> arrayList = new ArrayList<Integer>();

    @Test
    public void naiveAlgoritm () {
        byte[] data = {32,78,22,55,32,78};
        byte[] patternOld = {32,78};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data, patternOld);
        arrayList.add(0);
        arrayList.add(4);
        Assertions.assertEquals(arrayList,changeFiles.getReplacementPlace());


    }

    @Test
    public void replaceBytesLongerNewPatternRepBeg () {
        byte[] data = {32,78,22,55,32,78,11,32,78,11};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44,55,66};
        byte[] expected = {44,55,66,22,55,44,55,66,11,44,55,66,11};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }
    @Test
    public void replaceBytesShorterNewPatternRepBeg () {
        byte[] data = {32,78,22,55,32,78,11,32,78,11};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44};
        byte[] expected = {44,22,55,44,11,44,11};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }
    @Test
    public void replaceBytesSameLengNewPatternRepBeg () {
        byte[] data = {32,78,22,55,32,78,11,32,78,11};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44,55};
        byte[] expected = {44,55,22,55,44,55,11,44,55,11};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }
    @Test
    public void replaceBytesLongerNewPatternRepEnd () {
        byte[] data = {11,32,78,22,55,32,78,11,32,78};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44,55,66};
        byte[] expected = {11,44,55,66,22,55,44,55,66,11,44,55,66};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }
    @Test
    public void replaceBytesShorterNewPatternRepEnd () {
        byte[] data = {11,32,78,22,55,32,78,11,32,78};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44};
        byte[] expected = {11,44,22,55,44,11,44};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }
    @Test
    public void replaceBytesSameLengNewPatternRepEnd () {
        byte[] data = {11,32,78,22,55,32,78,11,32,78};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44,55};
        byte[] expected = {11,44,55,22,55,44,55,11,44,55};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }

    @Test
    public void replaceBytesLongerNewPatternRepBegEnd () {
        byte[] data = {32,78,22,55,32,78,11,32,78};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44,55,66};
        byte[] expected = {44,55,66,22,55,44,55,66,11,44,55,66};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }
    @Test
    public void replaceBytesShorterNewPatternRepBegEnd () {
        byte[] data = {32,78,22,55,32,78,11,32,78};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44};
        byte[] expected = {44,22,55,44,11,44};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }
    @Test
    public void replaceBytesSameLengNewPatternRepBegEnd () {
        byte[] data = {32,78,22,55,32,78,11,32,78};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44,55};
        byte[] expected = {44,55,22,55,44,55,11,44,55};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }

    @Test
    public void replaceBytesLongerNewPatternRepNoBegEnd () {
        byte[] data = {11,32,78,22,55,32,78,11,32,78,11};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44,55,66};
        byte[] expected = {11,44,55,66,22,55,44,55,66,11,44,55,66,11};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }
    @Test
    public void replaceBytesShorterNewPatternRepNoBegEnd () {
        byte[] data = {11,32,78,22,55,32,78,11,32,78,11};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44};
        byte[] expected = {11,44,22,55,44,11,44,11};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }
    @Test
    public void replaceBytesSameLengNewPatternRepNoBegEnd () {
        byte[] data = {11,32,78,22,55,32,78,11,32,78,11};
        byte[] patternOld = {32,78};
        byte[] patternNew = {44,55};
        byte[] expected = {11,44,55,22,55,44,55,11,44,55,11};
        ChangeFiles changeFiles = new ChangeFiles();
        changeFiles.naiveAlgoritm(data,patternOld);
        byte [] actual = changeFiles.replaceBytes(data,patternOld,patternNew);
        for (int i=0; i < changeFiles.getReplacementPlace().size();i++)
            System.out.print(changeFiles.getReplacementPlace().get(i)+",");
        System.out.println();
        for (int i=0; i < actual.length;i++)
            System.out.print(actual[i]+",");

        Assertions.assertTrue( Arrays.equals(expected, actual) );
    }



}
