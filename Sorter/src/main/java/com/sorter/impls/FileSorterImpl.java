package com.sorter.impls;

import com.sorter.enums.ContentType;
import com.sorter.enums.SortMode;
import com.sorter.enums.SorterErrorCode;
import com.sorter.exception.SorterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sorter.interfaces.FileSorter;
import com.sorter.interfaces.Setting;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Reads file, sorts it, creates new file and writes sorted data into created file
 * @param <T> comparable objects
 */
public class FileSorterImpl<T extends Comparable> implements FileSorter<T>{

    private Setting  setting;

    public FileSorterImpl(Setting setting) {
        this.setting = setting;
    }

    @Override
    public Setting getSetting() {
        return setting;
    }
    
    @Override
    public void setSetting(Setting setting) {
        this.setting = setting;
    }   
    
    /**
     * Reads data of files and adds it to list
     * @param file input file
     * @return Data of files as List
     */
    @Override
    public List<T> readFileToList(File file) throws SorterException {
        List list=null;
        if(file!=null && file.exists() && file.canRead() && file.isFile()){
            try(Scanner scanner = new Scanner(file)) {
                if(setting.getContentType() == ContentType.Integer) {
                    list = new ArrayList<Integer>();
                    while(scanner.hasNext()){
                        int item = scanner.nextInt();
                        list.add(item);
                    }
                } else if(setting.getContentType() == ContentType.String) {
                    list = new ArrayList<String>();
                    while(scanner.hasNext()){
                        if(scanner.hasNextInt()) {
                            throw new SorterException(SorterErrorCode.WRONG_DATA_IN_FILE);
                        }
                        String item = scanner.nextLine();
                        list.add(item);
                    }
                } 
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileSorterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    /**
     * Sorts data
     * @param list unsorted list 
     * @return sorted list  
     */
    @Override
    public List<T> sort(List<T> list) {
        SortMode sortMode = setting.getSortMode();
        if(sortMode==SortMode.ASC) {
            for (int i = 1; i < list.size(); i++) {
                int j=i;
                while(j>0 && (list.get(j).compareTo(list.get(j-1))<0)) {
                    T temp = list.get(j);
                    list.set(j,list.get(j-1));
                    list.set(j-1,temp);
                    j--;
                }
            }
        } else if(sortMode==SortMode.DESC) {
            for (int i = 1; i < list.size(); i++) {
                int j=i;
                while(j>0 && (list.get(j).compareTo(list.get(j-1))>0)) {
                    T temp = list.get(j);
                    list.set(j,list.get(j-1));
                    list.set(j-1,temp);
                    j--;
                }
            }
        }
        return list;
    }
    
    /**
     * Creates new file
     * @param prefix part of name from command line argument
     * @param file input file
     * @return created file
     * @throws java.io.IOException 
     */
    @Override
    public File createFile(String prefix, File file) throws IOException {
        String fileName = setting.getDirectoryName()+setting.getOutPrefix()+file.getName();
        File outPutFile = new File(fileName);
        outPutFile.getParentFile().mkdirs();
        outPutFile.createNewFile();
        return outPutFile; 
    }
    
    /**
     * Writes sorted data into new file
     * @param file output file
     * @param list sorted list
     * @throws IOException 
     */
    @Override
    public void writeListToFile(File file, List<T> list) throws IOException {
        PrintWriter pw = new PrintWriter(file);
        for (int i = 0; i < list.size(); i++) {
            pw.println(list.get(i));
        }
        pw.flush();
        pw.close();
    }  
}
