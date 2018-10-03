package com.topcheer.tools.common;

import net.sf.json.JSONObject;
import com.topcheer.tools.common.Responsemodel;

public interface JsonServiceInterface {

    public static final String INTERNAL_SUCCESS_CODE = "0000";
    public static final String INTERNAL_SUCCESS_MSG = "通讯成功";
    public static final String INTERNAL_ERROR_CODE = "EEEE";
    public static final String INTERNAL_ERROR_MSG = "内部错误";

    public Responsemodel  execute(JSONObject reqJson);
}
