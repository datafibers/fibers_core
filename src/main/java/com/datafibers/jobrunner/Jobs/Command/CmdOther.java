package com.datafibers.jobrunner.Jobs.Command;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class CmdOther extends CmdGeneric {

    // Max amount of output and Error data that we would return
    private final int DATA_RETURN_SIZE_THRESHOLD = 500000;// 500 kb

    // When there is too much data, we show the top and the bottom threshold/2 characters. This is the separator.
    private final String SEPARATOR = ".......................";

    public CmdEnum command;
    private HashMap<String,String> args;

    public CmdOther(CmdEnum cmd, HashMap<String,String> args){
        super(cmd, args);
        this.command = cmd;
        this.args = args;
    }

    public CmdEnum getCommand(){
        return command;
    }
    public HashMap<String,String> getArgs(){
        return args;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
       for(Map.Entry<String,String> entry: args.entrySet()){
        sb.append(entry.getKey()+":"+entry.getValue()+"\n");
        }
       return command.toString()+"\n"+sb.toString();
    }

    public boolean isValid(){
        return this.command != null;
    }

    public String getFileContent(File file) {
        if(!(file.exists() && file.canRead())){
            return "File doesn't exist or can't be read:"+file.getPath();
        }
        StringBuffer buffer = new StringBuffer();
        try(Reader reader=new FileReader(file)) {
            int fileSize = (int)file.length();
            if(fileSize> DATA_RETURN_SIZE_THRESHOLD){
                char [] chars = new char[DATA_RETURN_SIZE_THRESHOLD];
                reader.read(chars, 0, DATA_RETURN_SIZE_THRESHOLD / 2);
                buffer.append(chars);
                buffer.append("\n"+SEPARATOR+"\n");
                reader.skip(fileSize-DATA_RETURN_SIZE_THRESHOLD/2);
                reader.read(chars, 0, DATA_RETURN_SIZE_THRESHOLD / 2);
                buffer.append(chars);
            }
            else{
                char [] chars = new char[fileSize];
                reader.read(chars);
                buffer.append(chars);
            }
        }
        catch(IOException ex){
            return "Error reading file:" + "\n" + ex.getMessage();
        }

        return buffer.toString();
    }
}
