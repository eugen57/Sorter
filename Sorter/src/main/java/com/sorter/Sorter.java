package com.sorter;

import com.sorter.exception.SorterException;
import com.sorter.impls.CommonLineParserImpl;
import com.sorter.impls.DirectoryServiceImpl;
import com.sorter.impls.FileSorterTaskImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.sorter.interfaces.CommonLineParser;
import com.sorter.interfaces.DirectoryService;
import com.sorter.interfaces.FileSorterTask;
import com.sorter.interfaces.Setting;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application for sorting numbers(Integer) and Strings
 */
public class Sorter {
    public static void main(String[] args) {
        try {
        CommonLineParser commonLineParser = new CommonLineParserImpl();
        Setting setting = commonLineParser.parseCommonLine(args);
        DirectoryService directoryService = new DirectoryServiceImpl();
        List<File> files = directoryService.getFilesFromDirectory(setting.getDirectoryName());
        List<FileSorterTask> tasks = new ArrayList<>();
        for(File file:files){
            FileSorterTask task = new FileSorterTaskImpl(file,setting); 
            tasks.add(task);
            //task.startTask();
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.invokeAll(tasks);
        executorService.shutdown();
        } catch (SorterException e) {
            System.out.println(e.getErrorCode().getMessage());
        } catch (InterruptedException e) {
            Logger.getLogger(Sorter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
