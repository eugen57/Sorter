package com.sorter.enums;

/**
 * Error codes for exceptions
 */
public enum SorterErrorCode {
    INVALID_PARAMETERS("One of the parameters is invalid or "
                        + "number of parameters is more or less than four"),
    DIRECTORY_DOESNT_EXIST("Directory doesn't exist"),
    NOT_DIRECTORY("File with this path isn't a directory"),
    EMPTY_DIRECTORY("There're no files in directory"),
    NO_ACCESS_TO_DIRECTORY("No access to directory"),
    NO_ACCESS_TO_FILE("No access to file"),
    UNABLE_TO_CREATE_FILE("Unable  to create file"),
    WRONG_DATA_IN_FILE("Data type of file is different from argument of command line. "
                        + "File will not sorted");
    
    private String message;
    
    SorterErrorCode(String message) {
        this.message = message;
    }
    
    /**
     * 
     * @return error message
     */
    public String getMessage() {
        return message;
    }
}
