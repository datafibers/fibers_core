package com.datafibers.jobrunner.Requests;

import com.datafibers.jobrunner.Jobs.Command.CmdGeneric;

public class Request {
    public static enum REQUEST_TYPE {CREATE, INFO, MANAGE}

    public REQUEST_TYPE request;
    public CmdGeneric details;
    public Long jobId;

    public boolean isValid(){
        return details != null && details.isValid();
    }

    public String toString(){
        return request.toString() + "\n" + "details = " + "\n" + details.toString();
    }
}
