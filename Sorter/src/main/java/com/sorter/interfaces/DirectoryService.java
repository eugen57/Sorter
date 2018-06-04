package com.sorter.interfaces;

import com.sorter.exception.SorterException;
import java.io.File;
import java.util.List;

public interface DirectoryService {
    List<File> getFilesFromDirectory(String directoryName) throws SorterException;
}
