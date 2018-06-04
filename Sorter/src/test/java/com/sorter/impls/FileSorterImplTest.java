package com.sorter.impls;

import com.sorter.enums.ContentType;
import com.sorter.enums.SortMode;
import com.sorter.interfaces.Setting;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for FileSorterImpl
 */
public class FileSorterImplTest {
    
    public FileSorterImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readFileToList method, of class FileSorterImpl.
     */
    @Test
    public void testReadFileToList() {
        System.out.println("readFileToList");
    }

    /**
     * Test of sort method, of class FileSorterImpl.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        
        Setting setting = new SettingImpl("","",ContentType.Integer,SortMode.ASC);
        FileSorterImpl instance = new FileSorterImpl(setting);
        
        List<Integer> intsAsc = new ArrayList<>();
        intsAsc.add(5);
        intsAsc.add(1);
        intsAsc.add(3);
        intsAsc.add(2);
        intsAsc.add(7);
        instance.sort(intsAsc);
        Integer[] expIntsSortAsc = new Integer[]{1,2,3,5,7};
        assertArrayEquals(intsAsc.toArray(), expIntsSortAsc);
        
        setting.setSortMode(SortMode.DESC);
        List<Integer> intsDesc = new ArrayList<>();
        intsDesc.add(5);
        intsDesc.add(1);
        intsDesc.add(3);
        intsDesc.add(2);
        intsDesc.add(7);
        instance.sort(intsDesc);
        Integer[] expIntsSortDesc = new Integer[]{7,5,3,2,1};
        assertArrayEquals(intsDesc.toArray(), expIntsSortDesc);
        
        setting.setContentType(ContentType.String);
        setting.setSortMode(SortMode.ASC);
        List<String> strsAsc = new ArrayList<>();
        strsAsc.add("Peter");
        strsAsc.add("Eugene");
        strsAsc.add("Alex");
        strsAsc.add("Bob");
        strsAsc.add("Max");
        instance.sort(strsAsc);
        String[] expStrsSortAsc = new String[]{"Alex","Bob","Eugene","Max","Peter"};
        assertArrayEquals(strsAsc.toArray(), expStrsSortAsc);
        
        setting.setSortMode(SortMode.DESC);
        List<String> strsDesc = new ArrayList<>();
        strsDesc.add("Peter");
        strsDesc.add("Eugene");
        strsDesc.add("Alex");
        strsDesc.add("Bob");
        strsDesc.add("Max");
        instance.sort(strsDesc);
        String[] expStrsSortDesc = new String[]{"Peter","Max","Eugene","Bob","Alex"};
        assertArrayEquals(strsDesc.toArray(), expStrsSortDesc);
    }

    /**
     * Test of createFile method, of class FileSorterImpl.
     */
    @Test
    public void testCreateFile() throws Exception {
        System.out.println("createFile");
    }

    /**
     * Test of writeListToFile method, of class FileSorterImpl.
     */
    @Test
    public void testWriteListToFile() throws Exception {
        System.out.println("writeListToFile");
    }
}
