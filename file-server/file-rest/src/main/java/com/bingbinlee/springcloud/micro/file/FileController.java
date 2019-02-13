package com.bingbinlee.springcloud.micro.file;

import com.bingbinlee.springcloud.micro.file.model.FileResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = FileController.PATH)
public class FileController implements FileApi {

    private static Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileService fileService;

    @Override
    public FileResultModel queryByPK(@RequestBody FileResultModel fileResultModel) throws Exception {
        return fileService.queryByPK(fileResultModel);
    }
}
