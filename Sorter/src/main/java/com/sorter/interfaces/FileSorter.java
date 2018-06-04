package com.sorter.interfaces;

import com.sorter.exception.SorterException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileSorter<T> {
    Setting getSetting();
    void setSetting(Setting setting);
    List<T> readFileToList(File file) throws SorterException;
    List<T> sort(List<T> list);
    File createFile (String prefix,File file) throws IOException,SorterException;
    void writeListToFile(File file, List<T> list) throws IOException;
}
