package com.datafibers.jobrunner.Jobs.Command;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by duw3 on 5/24/2016.
 */
public class CmdHive extends CmdGeneric {

    private CmdEnum command;
    private HashMap<String,String> args;
    private String CmdStr = "ipconfig";

    public CmdHive(CmdEnum cmd, HashMap<String,String> args){
        super(cmd,args);
        System.out.println("This is to call CmdHive");
    }

    @Override
    public List<String> getCommandString() throws Exception{
        List<String> cmd = new ArrayList<String>();
        cmd.add(CmdStr);
        String arguments = getExecArgs();
        cmd.addAll(Splitter.on(" ").splitToList(arguments));
        return cmd;
    }

}
