package com.sorter.impls;

import com.sorter.enums.SorterErrorCode;
import com.sorter.exception.SorterException;
import java.io.File;
import java.util.List;
import com.sorter.interfaces.DirectoryService;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryServiceImpl implements DirectoryService{
    
    /**
     * @param directoryName path to the files directory
     * @return List of files
     * @throws SorterException 
     */
    @Override
    public List<File> getFilesFromDirectory(String directoryName) throws SorterException {
        if(Files.exists(Paths.get(directoryName))) {
            File directory = new File(directoryName);
            if(directory.isDirectory()) {
                if(directory.canRead()) {
                    File[] files = directory.listFiles();
                    if(files.length!=0) {
                        List<File> filesFromDirectory = new ArrayList<>();
                        filesFromDirectory.addAll(Arrays.asList(files));
                        return filesFromDirectory;
                    } else {
                        throw new SorterException(SorterErrorCode.EMPTY_DIRECTORY);
                    }
                } else {
                    throw new SorterException(SorterErrorCode.NO_ACCESS_TO_DIRECTORY);
                }
            } else {
                throw new SorterException(SorterErrorCode.NOT_DIRECTORY);
            }
        } else {
            throw new SorterException(SorterErrorCode.DIRECTORY_DOESNT_EXIST);
        }
    }    
}
