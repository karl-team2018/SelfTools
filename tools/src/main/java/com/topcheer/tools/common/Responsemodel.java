package com.topcheer.tools.common;

public class Responsemodel {

   private String rtCode;
   private String rtMsg;
   private Object rtData;


    public String getRtCode() {
        return rtCode;
    }

    public String getRtMsg() {
        return rtMsg;
    }

    public Object getRtData() {
        return rtData;
    }

    public void setRtCode(String rtCode) {
        this.rtCode = rtCode;
    }

    public void setRtMsg(String rtMsg) {
        this.rtMsg = rtMsg;
    }

    public void setRtData(Object rtData) {
        this.rtData = rtData;
    }

    @SuppressWarnings("unused")
    private Responsemodel(){
        super();
    }

    public Responsemodel(String rtCode,String rtMsg){
        super();
        this.rtCode = rtCode;
        this.rtMsg = rtMsg;
    }

    @Override
    public String toString(){
        return '{'+"rtCode:"+this.rtCode+",rtMsg:"+this.rtMsg+",rtData:"+this.rtData.toString()+'}';
    }
}
