package com.bingbinlee.springcloud.micro.file.dao.impl;

import com.bingbinlee.springcloud.micro.common.base.impl.BaseDataDaoImpl;
import com.bingbinlee.springcloud.micro.file.dao.FileDao;
import com.bingbinlee.springcloud.micro.file.domain.FileResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("fileDao")
public class FileDaoImpl extends BaseDataDaoImpl<FileResult> implements FileDao {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	

}
