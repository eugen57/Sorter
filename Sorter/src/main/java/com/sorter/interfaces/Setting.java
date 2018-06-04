package com.sorter.interfaces;

import com.sorter.enums.ContentType;
import com.sorter.enums.SortMode;

public interface Setting {
    String getDirectoryName();
    String getOutPrefix();
    ContentType getContentType();
    SortMode getSortMode();
    
    void setDirectoryName(String directoryName);
    void setOutPrefix(String outPrefix);
    void setContentType(ContentType contentType);
    void setSortMode(SortMode sortMode);
}
