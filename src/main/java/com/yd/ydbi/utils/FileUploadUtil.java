package com.yd.ydbi.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil
{
  private static Logger log = Logger.getLogger(FileUploadUtil.class);

  public static Iterator<FileItem> getFileItemIterator(HttpServletRequest request, String tempFolderPath) throws FileUploadException
  {
    try
    {
      request.setCharacterEncoding("utf-8");
    } catch (UnsupportedEncodingException e) {
      log.error("文件上传编码错误:" + e.getMessage());
    }
    File tempUploadFolder = new File(tempFolderPath);
    if ((!tempUploadFolder.exists()) || (!tempUploadFolder.isDirectory())) {
      tempUploadFolder.mkdirs();
    }
    DiskFileItemFactory factory = new DiskFileItemFactory(
      10240, tempUploadFolder);
    ServletFileUpload upload = new ServletFileUpload(factory);
    List items = upload.parseRequest(request);
    Iterator it = items.iterator();
    return it;
  }

  public static String getFileName(FileItem item) throws Exception {
    String originalPath = item.getName();
    String originalFileName = null;
    if (originalPath.contains("\\"))
      originalFileName = originalPath.substring(originalPath
        .lastIndexOf("\\") + 1);
    else {
      originalFileName = originalPath.substring(originalPath
        .lastIndexOf("/") + 1);
    }
    return originalFileName;
  }

  public static void uploadFile(FileItem item, String filePath) throws Exception
  {
    File uploadedFile = new File(filePath);
    uploadedFile.createNewFile();
    item.write(uploadedFile);
  }

  public static boolean isZip(String fileName)
  {
    String fileType = fileName.substring(fileName.lastIndexOf("."));
    if ((".zip".equalsIgnoreCase(fileType)) || (".rar".equalsIgnoreCase(fileType))) {
      return true;
    }
    return false;
  }

  public static boolean isPic(String fileName)
  {
    String fileType = fileName.substring(fileName.lastIndexOf("."));
    if ((".jpg".equalsIgnoreCase(fileType)) || 
      (".jpeg".equalsIgnoreCase(fileType)) || 
      (".gif".equalsIgnoreCase(fileType)) || 
      (".png".equalsIgnoreCase(fileType))) {
      return true;
    }

    return false;
  }

  public static String saveFile(MultipartFile file, String path) {
    String filePath = null;

    if (!file.isEmpty()) {
      try
      {
        filePath = path + file.getOriginalFilename() + "." + 
          new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        file.transferTo(new File(filePath));
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    return filePath;
  }
}