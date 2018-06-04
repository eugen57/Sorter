package com.sorter.interfaces;

import com.sorter.exception.SorterException;
import java.io.IOException;
import java.util.concurrent.Callable;

public interface FileSorterTask extends Callable<Boolean> {
    boolean startTask() throws IOException, SorterException;
}
