package com.bingbinlee.springcloud.micro.file.domain;

import com.bingbinlee.springcloud.micro.common.base.model.BaseDataModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "FileResult")
public class FileResult extends BaseDataModel implements Serializable {

    private String fileID;

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }


}