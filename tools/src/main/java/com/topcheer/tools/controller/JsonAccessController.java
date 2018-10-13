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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = {"/data/test"},method = {RequestMethod.GET})
    public JSONObject serviceGetData(HttpServletRequest req){
        String testdata = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[{\"id\":10000,\"username\":\"user-0\",\"sex\":\"女\",\"city\":\"城市-0\",\"sign\":\"签名-0\",\"experience\":255,\"logins\":24,\"wealth\":82830700,\"classify\":\"作家\",\"score\":57},{\"id\":10001,\"username\":\"user-1\",\"sex\":\"男\",\"city\":\"城市-1\",\"sign\":\"签名-1\",\"experience\":884,\"logins\":58,\"wealth\":64928690,\"classify\":\"词人\",\"score\":27},{\"id\":10002,\"username\":\"user-2\",\"sex\":\"女\",\"city\":\"城市-2\",\"sign\":\"签名-2\",\"experience\":650,\"logins\":77,\"wealth\":6298078,\"classify\":\"酱油\",\"score\":31},{\"id\":10003,\"username\":\"user-3\",\"sex\":\"女\",\"city\":\"城市-3\",\"sign\":\"签名-3\",\"experience\":362,\"logins\":157,\"wealth\":37117017,\"classify\":\"诗人\",\"score\":68},{\"id\":10004,\"username\":\"user-4\",\"sex\":\"男\",\"city\":\"城市-4\",\"sign\":\"签名-4\",\"experience\":807,\"logins\":51,\"wealth\":76263262,\"classify\":\"作家\",\"score\":6},{\"id\":10005,\"username\":\"user-5\",\"sex\":\"女\",\"city\":\"城市-5\",\"sign\":\"签名-5\",\"experience\":173,\"logins\":68,\"wealth\":60344147,\"classify\":\"作家\",\"score\":87},{\"id\":10006,\"username\":\"user-6\",\"sex\":\"女\",\"city\":\"城市-6\",\"sign\":\"签名-6\",\"experience\":982,\"logins\":37,\"wealth\":57768166,\"classify\":\"作家\",\"score\":34},{\"id\":10007,\"username\":\"user-7\",\"sex\":\"男\",\"city\":\"城市-7\",\"sign\":\"签名-7\",\"experience\":727,\"logins\":150,\"wealth\":82030578,\"classify\":\"作家\",\"score\":28},{\"id\":10008,\"username\":\"user-8\",\"sex\":\"男\",\"city\":\"城市-8\",\"sign\":\"签名-8\",\"experience\":951,\"logins\":133,\"wealth\":16503371,\"classify\":\"词人\",\"score\":14},{\"id\":10009,\"username\":\"user-9\",\"sex\":\"女\",\"city\":\"城市-9\",\"sign\":\"签名-9\",\"experience\":484,\"logins\":25,\"wealth\":86801934,\"classify\":\"词人\",\"score\":75}]}";
        System.out.println(req.getParameterMap().toString());
        return JSONObject.fromObject(testdata);
    }

}
