package com.topcheer.tools.common;

import com.topcheer.tools.Resposity.MenuRepository;
import com.topcheer.tools.entity.MenuUrl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
public class GetMenuList {

    private final static Logger logger = LoggerFactory.getLogger(GetMenuList.class);

    @Autowired
    MenuRepository menuRepository;


    public JSONObject getByRole(Integer roleid) {

        //一级菜单
        List<MenuUrl> listMenu1 = menuRepository.findAllByRolesId1(roleid);
        //二级菜单
        List<MenuUrl> listMenu2 = menuRepository.findAllByRolesId2(roleid);
        JSONObject json = new JSONObject();
        if (listMenu1.size() > 0) {
            for (MenuUrl menu : listMenu1) {
                if (json.containsKey(menu.getBiz_block())) {
                    JSONObject json0 = new JSONObject();
                    json0.put("title", menu.getTitle());
                    json0.put("icon", menu.getIcon());
                    json0.put("href", menu.getHref());
                    json0.put("spread", menu.getSpread());
                    json0.put("noteid", menu.getNoteid());
                    json0.put("target",menu.getTarget());
                    JSONArray jsonA = (JSONArray) (json.get(menu.getBiz_block()));
                    jsonA.add(json0);
                    json.put(menu.getBiz_block(), jsonA);

                } else {
                    JSONArray jsonArray = new JSONArray();

                    JSONObject json1 = new JSONObject();
                    json1.put("title", menu.getTitle());
                    json1.put("icon", menu.getIcon());
                    json1.put("href", menu.getHref());
                    json1.put("spread", menu.getSpread());
                    json1.put("noteid", menu.getNoteid());
                    json1.put("target",menu.getTarget());
                    jsonArray.add(json1);
                    json.put(menu.getBiz_block(), jsonArray);
                }

            }

            for (MenuUrl menu : listMenu2) {
                if (json.containsKey(menu.getBiz_block())) {
                    JSONArray jsonA = (JSONArray) (json.get(menu.getBiz_block()));
                    int isize = jsonA.size();
                    JSONArray jsonResult = new JSONArray();
                    for (int i = 0; i < isize; i++) {
                        JSONObject jsonObject = jsonA.getJSONObject(i);
                        if (jsonObject.optInt("noteid") == menu.getParentnoteid()) {
                            System.out.println("test");
                            if (jsonObject.containsKey("children")) {
                                JSONArray jsona002 = (JSONArray) jsonObject.get("children");
                                JSONObject json001 = new JSONObject();
                                json001.put("title", menu.getTitle());
                                json001.put("icon", menu.getIcon());
                                json001.put("href", menu.getHref());
                                json001.put("spread", menu.getSpread());
                                json001.put("noteid", menu.getNoteid());
                                json001.put("target",menu.getTarget());
                                jsona002.add(json001);
                                jsonObject.put("children", jsona002);


                            } else {
                                JSONArray jsona001 = new JSONArray();
                                JSONObject json001 = new JSONObject();
                                json001.put("title", menu.getTitle());
                                json001.put("icon", menu.getIcon());
                                json001.put("href", menu.getHref());
                                json001.put("spread", menu.getSpread());
                                json001.put("noteid", menu.getNoteid());
                                json001.put("target",menu.getTarget());
                                jsona001.add(json001);
                                jsonObject.put("children", jsona001);
                            }

                        }
                        jsonResult.add(jsonObject);

                    }

                    json.put(menu.getBiz_block(), jsonResult);
                }

            }


        }
        logger.info("user roleid:"+roleid+"===>"+json.toString());
        return json;
    }
}
