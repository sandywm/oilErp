package com.oil.tools;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.upload.FormFile;

public class CheckImage
{

    public CheckImage()
    {
    }

    public boolean checkImageStuffix(String suffix){
        if(suffix.equalsIgnoreCase(".jpg") || suffix.equalsIgnoreCase(".jpeg")){
            return true;
        }else if(suffix.equalsIgnoreCase(".bmp")){
        	return true;
        }else if(suffix.equalsIgnoreCase(".gif")){
        	return true;
        }else if(suffix.equalsIgnoreCase(".png")){
        	return true;
        }else{
        	return false;
        }
    }
    
    /**
     * 获取上传文件类型
     * @description
     * @author wm
     * @date 2018-9-4 上午08:47:47
     * @param suffix
     * @return
     */
    public String getUpFileStuffix(String suffix){
    	suffix = suffix.toLowerCase();
    	String gzStr = "xls,xlsx";
    	String fileType = "";
    	if(gzStr.indexOf(suffix) >= 0){
    		fileType = "file";
    	}
    	return fileType;
    }
    
    public boolean checkImageSize(FormFile file, int size){
        return file.getFileSize() <= size;
    }
    public boolean checkImageSize_new(File file, int size){
        return file.length() <= size;
    }
    public boolean checkFileSize(FormFile file,int size){
    	return file.getFileSize() <= size;
    }
    /**
     * 文件大小限制
     * @description
     * @author wm
     * @date 2016-8-12 上午08:40:06
     * @param fileItem
     * @param size byte
     * @return
     */
    public boolean checkItemSize(FileItem fileItem,int size){
    	return fileItem.getSize() <= size;
    }
}