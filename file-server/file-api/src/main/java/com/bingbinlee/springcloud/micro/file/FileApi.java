package com.bingbinlee.springcloud.micro.file;

import com.bingbinlee.springcloud.micro.FileConstant;
import com.bingbinlee.springcloud.micro.file.model.FileResultModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = FileConstant.FILE_SERVICE_NAME, path = FileApi.PATH)
public interface FileApi {
    public static final String PATH = "fileServer";


    @RequestMapping(value = "queryByPK", method = {RequestMethod.POST})
    FileResultModel queryByPK(@RequestBody FileResultModel fileResultModel) throws Exception;


}
