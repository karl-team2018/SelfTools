package com.topcheer.tools.controller;


import com.topcheer.tools.Resposity.MenuRepository;
import com.topcheer.tools.common.GetMenuList;
import com.topcheer.tools.common.JsonServiceInterface;
import com.topcheer.tools.common.Responsemodel;
import com.topcheer.tools.entity.MenuUrl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
public class JsonAccessController {

    @Autowired
    public ApplicationContext applicationContext;

    @Autowired
    public GetMenuList getMenuList;



    @RequestMapping(value = {"*/srv.call","*/srv.do"},method = {RequestMethod.POST},produces = "application/json;utf-8")
    public Responsemodel serviceCall(@RequestParam(value = "req",required = true) String request) throws UnsupportedEncodingException {

        Responsemodel rm = new Responsemodel(JsonServiceInterface.INTERNAL_SUCCESS_CODE,
                JsonServiceInterface.INTERNAL_SUCCESS_MSG);
        String reqStr = URLDecoder.decode(request,"UTF-8");
        JSONObject reqJson = JSONObject.fromObject(reqStr);
        System.out.println(reqStr);

        String srvId = reqJson.optString("SERVICE_ID");
        if(srvId != null && !"".equals(srvId)){
            Object jsonService = applicationContext.getBean(srvId);
            if(jsonService instanceof JsonServiceInterface){
                return ((JsonServiceInterface) jsonService).execute(reqJson);
            }
        }else{
            rm.setRtCode("EE00");
            rm.setRtData("");
        }

        return rm;
    }

    @RequestMapping(value = {"/data/menu"},method = {RequestMethod.GET})
    public JSONObject serviceGetMenuList() {

        return getMenuList.getByRole(102);
    }

}
