package com.datafibers.jobrunner.Jobs;

import com.datafibers.jobrunner.Jobs.Command.CmdOther;

import java.util.Date;
import java.util.concurrent.Callable;

public abstract class Job implements Callable{
    public enum JOB_STATUS {
        WAITING, RUNNING, FINISHED, ERROR
    }

    private Long id;
    private JOB_STATUS status;

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private Date startTime;
    private Date endTime;
    private CmdOther jobCommand;
    private JobType type;

    public Job(Long id){this.id=id;}

    public abstract void setupJob() throws Exception;

    @Override
    public abstract Job call();

    public Long getId() {
        return id;
    }

    public JOB_STATUS getStatus() {
        return status;
    }

    public void setStatus(JOB_STATUS status) {
        this.status = status;
    }

    public abstract String getErrors();

    public abstract String getOutput();

    public abstract boolean tryCancel(); // try to cancel this job

    public abstract String getJobInfo();

    public Date getStartTime(){
        return startTime;
    }

    public Date getEndTime(){
        return endTime;
    }

    public String convertDateToString(Date date){
        if(date == null) return "Not started yet";
        else return date.toString();
    }

    public String getStartTimeStr(){
        return convertDateToString(startTime);
    }

    public String getEndTimeStr(){
        return convertDateToString(endTime);
    }

    public CmdOther getJobCommand(){
        return jobCommand;
    }

    public JobType getJobType(){
        return type;
    }

}
