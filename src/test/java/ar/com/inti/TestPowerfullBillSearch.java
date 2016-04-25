package ar.com.inti;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runner.RunWith;

import java.io.File;
import java.lang.ClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ivan on 13/04/16.
 */

@RunWith(Parameterized.class)
public class TestPowerfullBillSearch {

    private static List<Bill> haystack = null;
    private BillSearch bs = new PowerfulBillSearch(new PcImageTestFactory());
    private ClassLoader classLoader = getClass().getClassLoader();
    private String testBillPath;
    private String expectedResult;


    public TestPowerfullBillSearch(String path, String result){
        this.testBillPath = path;
        this.expectedResult = result;
    }

    private static List <Bill> load_templates(){
        String path = TestPowerfullBillSearch.class.getClassLoader().getResource("templates").getPath();
        FileBillDAO file_loader = new FileBillDAO(path, new ImageDirectoryBillFactory(new ImageFileBillImageFactory()));
        return file_loader.loadAll();
    }

    private static Collection<Object[]> load_testFiles() {
        ArrayList<Object[]> data = new  ArrayList<Object[]>();
        ClassLoader classLoader = TestPowerfullBillSearch.class.getClassLoader();
        File directory = new File(classLoader.getResource("datum").getPath());
        File[] filesList = directory.listFiles();
        for (File file : filesList) {
            if (file.isDirectory() & (file.getName().compareToIgnoreCase("templates") != 0)) {
                String er = file.getName();
                for (File billImages : file.listFiles()) {
                    String[] paths = new String[2];
                    paths[1] = er;
                    paths[0] = billImages.getPath();
                    data.add(paths);
                }
            }
        }
        return data;
    }

    // creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        return load_testFiles();
    }

    @BeforeClass
    public static void setup(){
         haystack = load_templates();
    }

    @Test
    public void testImage() {
        ImageFileBillImageFactory needleFactory = new ImageFileBillImageFactory();
        File testBillFile = new File(this.testBillPath);
        BillImage needle = needleFactory.createFromImageFile(testBillFile);
        assertEquals(this.expectedResult, bs.search(needle, haystack));

    }

}
