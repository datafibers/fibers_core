package com.datafibers.jobrunner.Jobs.Factory;

import com.datafibers.jobrunner.Jobs.Command.CmdGeneric;
import com.datafibers.jobrunner.config.Config;
import com.datafibers.jobrunner.Jobs.Executable;
import com.datafibers.jobrunner.Jobs.Job;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class JobFactory {
    public static Job Create(CmdGeneric command) throws IOException{
        Job job = null;
        switch(command.getCommand()){
            case EXECUTE:
                Long jobId = new Date().getTime();
                createJobWorkingDirectory(jobId.toString());
                File stdout = createFile("stdout", jobId.toString());
                File stderr = createFile("stderr", jobId.toString());
                job = new Executable(jobId, stdout, stderr, command);
            case CASCADING:
                break;
            default:
                System.err.println("Unknown job creation type: " + command.toString());
        }
        return job;
    }

    private static String getJobWorkingDirectory(String jobName) throws IOException{
        String baseWorkingDir = getWorkingDirectory();
        return baseWorkingDir + "/" + jobName;
    }

    private static boolean createJobWorkingDirectory(String jobName) throws IOException{
        File jobWorkingDirectory = new File(getJobWorkingDirectory(jobName));
        return jobWorkingDirectory.mkdir();
    }

    private static File createFile(String fileName, String jobName) throws IOException{
        String jobDir=getJobWorkingDirectory(jobName);
        File file = new File(jobDir + "/" + fileName);
        file.createNewFile();
        return file;
    }

    /**
     * Gets the working directory for this job manager. All job related directories and files will be put in this location
     * @return
     * @throws IOException
     */
    private static String getWorkingDirectory() throws IOException{
        String workingDir;
        try{
            workingDir = Config.getInstance().getProperty("working_dir");
            if(workingDir == null){
                System.err.println("Can't find config option: 'working_dir'. Cannot create job");
            }
        }catch(IOException ex){
            throw ex;
        }
        return workingDir;
    }

}
