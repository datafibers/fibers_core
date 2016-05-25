package com.datafibers.jobrunner.Jobs.Command;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CmdGeneric {

    private CmdEnum command;
    private HashMap<String,String> args;

    public CmdGeneric(CmdEnum cmd, HashMap<String,String> args){
        this.command = cmd;
        this.args = args;
    }

    public CmdEnum getCommand(){
        return command;
    }

    public HashMap<String,String> getArgs(){
        return args;
    }

    public String getExec(){
        return args.get("EXECUTABLE");
    }

    public String getExecArgs(){
        return args.get("ARGS");
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String> entry: args.entrySet()){
            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        }
        return "command = " + command.toString() + "\n" + "args = " + "\n" + sb.toString();
    }

    public boolean isValid(){
        return this.command != null;
    }

    public List<String> getCommandString() throws Exception{
        List<String> cmd = new ArrayList<String>();
        String execCommand = getExec();
        cmd.add(execCommand);
        String arguments = getExecArgs();
        cmd.addAll(Splitter.on(" ").splitToList(arguments));
        return cmd;
    }

    //how to fetch the error or data for this type of command
    //abstract String getFileContent(File file);
}
