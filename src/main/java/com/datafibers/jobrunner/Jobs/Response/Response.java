package com.datafibers.jobrunner.Jobs.Response;

public class Response {
    public enum RESPONSE_STATUS {OK, ERROR}
    public String message;
    public RESPONSE_STATUS status;
}
