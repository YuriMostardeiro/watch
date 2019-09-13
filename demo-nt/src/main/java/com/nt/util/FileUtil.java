package com.nt.util;

import java.io.File;

public class FileUtil {

    public static void createDirectory(String folder) {
        File file = new File(folder);
        if (!file.exists())
            file.mkdir();
    }

    public static String replaceAscIIDelimiter(String code){
        return code.replaceAll("[^\\p{ASCII}]", ";");
    }
}
