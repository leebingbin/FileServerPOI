package com.bingbinlee.springcloud.micro.common.base.model;

import java.io.Serializable;
import java.util.Date;

public class BaseDataModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date ctime = new Date();
    private String md5Value;


}
