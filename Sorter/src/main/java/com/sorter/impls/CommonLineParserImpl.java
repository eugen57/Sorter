package com.sorter.impls;
import com.sorter.enums.ContentType;
import com.sorter.enums.SortMode;
import com.sorter.enums.SorterErrorCode;
import com.sorter.exception.SorterException;
import com.sorter.interfaces.CommonLineParser;
import com.sorter.interfaces.Setting;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.DefaultParser;

/**
 * Command line parser
 */
public class CommonLineParserImpl implements CommonLineParser{
    
    private CommandLineParser cmdLineParser;
    private Options options;

    public CommonLineParserImpl() {
        this.cmdLineParser = new DefaultParser();
        this.options = new Options();
        addOptions();
    }
    
    /**
     * Adds possible options of command line arguments
     */
    private void addOptions() {
        Option outPrefixOption = new Option("o", "out-prefix", true, "outprefix");
        outPrefixOption.setArgs(1);
        
        Option contentTypeOption = new Option("c", "content-type",true, "contenttype");
        contentTypeOption.setArgs(1);

        Option sortModeOption = new Option("s", "sort-mode",true, "sortmode");
        sortModeOption.setArgs(1);
        
        options.addOption(outPrefixOption);
        options.addOption(contentTypeOption);
        options.addOption(sortModeOption);
    }
    
    /**
     * 
     * @param args command line arguments
     * @return Setting object
     * @throws SorterException if there are any problems with arguments 
     */
    @Override
    public Setting parseCommonLine(String[] args) throws SorterException {
        Setting setting = null;
        if(args.length==4) {
            String directoryName = args[0];
            String outPrefix = null;
            ContentType contentType = null;
            SortMode sortMode = null;
            try {
                CommandLine commandLine = cmdLineParser.parse(options, args);
                if(commandLine.hasOption("out-prefix") &&
                        commandLine.hasOption("content-type") &&
                        commandLine.hasOption("sort-mode")) {
                    outPrefix = commandLine.getOptionValue("out-prefix");
                    
                    String contentTypeString = commandLine.getOptionValue("content-type");
                    if(contentTypeString.equalsIgnoreCase("i")) {
                       contentType = ContentType.Integer;
                    } else if(contentTypeString.equals("s")) {
                       contentType = ContentType.String;
                    }
                    
                    String sortModeString = commandLine.getOptionValue("sort-mode");
                    if(sortModeString.equalsIgnoreCase("a")) {
                       sortMode = SortMode.ASC;
                    } else if(sortModeString.equals("d")) {
                       sortMode = SortMode.DESC;
                    }
                } 

                if(outPrefix!=null && contentType!=null && sortMode!=null) {
                    setting = new SettingImpl(directoryName, outPrefix, contentType, sortMode);
                } else {
                    throw new SorterException(SorterErrorCode.INVALID_PARAMETERS);
                }
            } catch (ParseException ex) {
                Logger.getLogger(CommonLineParserImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new SorterException(SorterErrorCode.INVALID_PARAMETERS);
        }
        return setting;
    }
}
