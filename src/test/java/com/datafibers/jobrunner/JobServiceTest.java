package com.datafibers.jobrunner;

import com.datafibers.jobrunner.Jobs.Command.CmdEnum;
import com.datafibers.jobrunner.Jobs.Command.CmdGeneric;
import com.datafibers.jobrunner.Jobs.Command.CmdOther;
import com.datafibers.jobrunner.Jobs.Response.Response;
import com.datafibers.jobrunner.Requests.Request;
import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.*;

public class JobServiceTest {

    @Test
    public void testRunCommand() throws Exception {


    }

    @Test
    public void testInvalidCreateCommand() throws Exception {
        JobService service = new JobService();
        Request req = new Request();
        req.request= Request.REQUEST_TYPE.CREATE;

        HashMap<String,String> commandArgs = new HashMap<>();
        req.details = new CmdOther(com.datafibers.jobrunner.Jobs.Command.CmdEnum.EXECUTE, commandArgs) {
        };

        //no command args supplied to create command, so it should give back and error instead of a job id
        Response response =service.runCommand(req);
        assertEquals(response.status, Response.RESPONSE_STATUS.ERROR);
    }


    @Test
    public void testValidCreateCommand() throws Exception {
        JobService service = new JobService();
        Request req = new Request();
        req.request= Request.REQUEST_TYPE.CREATE;

        HashMap<String,String> commandArgs = new HashMap<>();
        commandArgs.put("EXECUTABLE","dir");
        commandArgs.put("ARGS","");
        req.details= new CmdOther(CmdEnum.EXECUTE, commandArgs);

        Response response =service.runCommand(req);
        assertEquals(response.status,Response.RESPONSE_STATUS.OK);
        String res = response.message;
        assertTrue(isALong(res));
    }

    /**
     * Checks whether a number is a Long. Required since Java doesn't have TryParse.
     * @param value
     * @return
     */
    boolean isALong(String value){
        try{
            Long.parseLong(value);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
}