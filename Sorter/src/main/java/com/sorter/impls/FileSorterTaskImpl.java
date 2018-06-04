package com.sorter.impls;

import com.sorter.exception.SorterException;
import com.sorter.interfaces.FileSorter;
import java.io.File;
import com.sorter.interfaces.FileSorterTask;
import com.sorter.interfaces.Setting;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Callable task, which does all sorting operations
 */
public class FileSorterTaskImpl implements FileSorterTask, Callable<Boolean>{
    
    private FileSorter fileSorter;
    private File file;

    public FileSorterTaskImpl(File file, Setting setting) {
        fileSorter = new FileSorterImpl(setting);
        this.file = file;
    }
    
    /**
     * Does all sorting operations
     * @return true if file was successfully sorted and writed to new file
     * @throws SorterException 
     */
    @Override
    public boolean startTask() throws SorterException {
        try {
            List list = fileSorter.readFileToList(file);
            List sortedList = fileSorter.sort(list);
            File outPutFile = fileSorter.createFile(fileSorter.getSetting().getOutPrefix(), file);
            fileSorter.writeListToFile(outPutFile, sortedList);
            return true;
        }  catch (SorterException e) {
            System.out.println(e.getErrorCode().getMessage());
            return false;
        }  catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean call() throws SorterException {
        return startTask();
    }
}
