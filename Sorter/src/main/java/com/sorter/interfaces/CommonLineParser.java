package com.sorter.interfaces;

import com.sorter.exception.SorterException;

public interface CommonLineParser {
    Setting parseCommonLine(String[] args) throws SorterException;
}
