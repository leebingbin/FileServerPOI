package com.bingbinlee.springcloud.micro.file;

import com.bingbinlee.springcloud.micro.file.model.FileResultModel;

public interface FileService {

    FileResultModel queryByPK(FileResultModel fileResultModel) throws Exception;

}
