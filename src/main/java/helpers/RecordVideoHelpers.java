package helpers;

import atu.testrecorder.ATUTestRecorder;
import utilities.PropertiesFile;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordVideoHelpers {
    // ------Record with ATU library-----------
    public static ATUTestRecorder recorder;
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    static Date date = new Date();

    public static void startRecordATU(String videoName) throws Exception {
        PropertiesFile.setPropertiesFile();
        File theDir = new File(PropertiesFile.getPropValue("exportVideoPath"));
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        recorder = new ATUTestRecorder(PropertiesFile.getPropValue("exportVideoPath"), videoName + "-" + dateFormat.format(date), false);
        recorder.start();
    }

    public static void stopRecordATU() throws Exception {
        recorder.stop();
    }
}
