package com.topcheer.tools.entity;

import javax.persistence.*;

@Entity
@Table(name="t_menuurl")
public class MenuUrl {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="noteid")
    private Integer noteid;

    @Column(name="parentnoteid")
    private Integer parentnoteid;

    @Column(name="biz_block")
    private String biz_block;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public Integer getParentnoteid() {
        return parentnoteid;
    }

    public void setParentnoteid(Integer parentnoteid) {
        this.parentnoteid = parentnoteid;
    }

    public String getBiz_block() {
        return biz_block;
    }

    public void setBiz_block(String biz_block) {
        this.biz_block = biz_block;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(Integer roles_id) {
        this.roles_id = roles_id;
    }

    @Column(name = "title")
    private String title;

    @Column(name = "icon")
    private String icon;

    @Column(name = "href")
    private String href;

    @Column(name = "spread")
    private String spread;

    @Column(name="target")
    private  String target;

    @Column(name = "roles_id")
    private Integer roles_id;

    @Override
    public String toString(){
        return "menuurl:"+"biz_block:"+biz_block;
    }


}
