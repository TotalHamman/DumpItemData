package totalhamman.dumpitemdata.utils;

import totalhamman.dumpitemdata.DumpItemData;

import static totalhamman.dumpitemdata.DumpItemData.debugOn;

public class LogHelper {

    public static void logHelper(String msg) {
        if (debugOn) DumpItemData.log.info(msg);
    }

}
