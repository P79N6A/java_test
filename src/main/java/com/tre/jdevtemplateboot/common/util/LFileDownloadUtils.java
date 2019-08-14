package com.tre.jdevtemplateboot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @description: 文件下载工具
 * @author: JDev
 * @create: 2018-12-20 14:08
 **/

public class LFileDownloadUtils {

    private final static Logger logger = LoggerFactory.getLogger(LFileDownloadUtils.class);

    public static void FileDownload(String filePath,String filename,HttpServletRequest request, HttpServletResponse response)  {

        File file = new File(filePath+"\\"+filename);
        try{
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] e = new byte[inputStream.available()];
            inputStream.read(e);
            inputStream.close();
            response.reset();
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            os.write(e);
            os.flush();
            os.close();
        }
        catch (Exception e){
            logger.error("======File download exception======", e);
        }
    }

}
