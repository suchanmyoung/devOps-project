package com.x1.chan.common;

import com.x1.chan.domain.FileVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Slf4j
public class FileUtils {
    public static FileVO insertMultipleFile(FileVO fileVo, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String dftFilePath = request.getSession().getServletContext().getRealPath("/");
        log.info("dtfFilePath = {}", dftFilePath);
        String filePath = dftFilePath + "resources" + File.separator + "upload" + File.separator;
        log.info("filePath ={}", filePath);
        String originalFileName = request.getHeader("file-name");
        String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        log.info("origin File Exten = {} ", originalFileExtension);

        originalFileExtension = originalFileExtension.toLowerCase();
        String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;

        File file = new File(filePath);
        if(!file.exists()) file.mkdirs();
        InputStream inputStream = request.getInputStream();
        OutputStream outputStream = new FileOutputStream(filePath + storedFileName);

        int numRead;
        byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
        while ((numRead = inputStream.read(b, 0, b.length)) != -1) {
            outputStream.write(b, 0, numRead);
        }
        if(inputStream != null){
            inputStream.close();
        }
        outputStream.flush();
        outputStream.close();

        FileVO returnFileVO = new FileVO();
        returnFileVO.setOriginalFileName(originalFileName);
        returnFileVO.setStoredFileName(storedFileName);
        returnFileVO.setFileSize(Long.valueOf(Integer.parseInt(request.getHeader("file-size"))));
        returnFileVO.setFileExt(originalFileName.substring(originalFileName.lastIndexOf(".")));
        returnFileVO.setFilePath(filePath);

        String sFileInfo ="";
        sFileInfo += "&bNewLine=true";
        sFileInfo += "&sFileName=" + originalFileName;
        sFileInfo += "&sFileURL" + "/resources/upload/" + storedFileName;
        PrintWriter printWriter = response.getWriter();
        printWriter.print(sFileInfo);
        printWriter.flush();
        printWriter.close();
        return returnFileVO;
    }

}
