package com.bingbinlee.springcloud.micro.file.service.impl;

import com.bingbinlee.springcloud.micro.file.FileService;
import com.bingbinlee.springcloud.micro.file.dao.FileDao;
import com.bingbinlee.springcloud.micro.file.model.FileResultModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

    @Override
    public FileResultModel queryByPK(FileResultModel fileResultModel) throws Exception {
        return null;
    }

}
