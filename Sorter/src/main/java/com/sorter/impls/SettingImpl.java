package com.sorter.impls;

import com.sorter.enums.ContentType;
import com.sorter.enums.SortMode;
import com.sorter.interfaces.Setting;

/**
 * Class, which contains all values of command line arguments
 */
public class SettingImpl implements Setting{
    
    private String directoryName;
    private String outPrefix;
    private ContentType contentType;
    private SortMode sortMode;

    public SettingImpl() {
    }

    public SettingImpl(String directoryName, String outPrefix, ContentType contentType, SortMode sortMode) {
        this.directoryName = directoryName;
        this.outPrefix = outPrefix;
        this.contentType = contentType;
        this.sortMode = sortMode;
    }
    
    /**
     * @return directory name of files
     */
    @Override
    public String getDirectoryName() {
        return directoryName;
    }
    
    /**
     * @return prefix of name of new files
     */
    @Override
    public String getOutPrefix() {
        return outPrefix;
    }
    
    /**
     * @return data type of files
     */
    @Override
    public ContentType getContentType() {
        return contentType;
    }
    
    /**
     * @return sort mode
     */
    @Override
    public SortMode getSortMode() {
        return sortMode;
    }

    @Override
    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public void setOutPrefix(String outPrefix) {
        this.outPrefix = outPrefix;
    }

    @Override
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public void setSortMode(SortMode sortMode) {
        this.sortMode = sortMode;
    }
}
