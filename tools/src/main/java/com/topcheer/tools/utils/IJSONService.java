package com.topcheer.tools.utils;

import net.sf.json.JSONObject;

import java.util.Map;

public interface IJSONService {
    JSONObject execute(Map<String, Object> reqObj);
}
