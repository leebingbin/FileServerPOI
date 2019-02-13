package com.bingbinlee.springcloud.micro.file.model;

import java.io.Serializable;

public class FileResultModel implements Serializable {

    private String fileID;

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }


}