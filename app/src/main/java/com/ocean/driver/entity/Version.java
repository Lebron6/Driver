package com.ocean.driver.entity;

/**
 * Created by James on 2020/12/17.
 */
public class Version {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1608111183
     * data : {"version":"1.2","download":"http://img.oceanscm.com/andriod/driver.apk"}
     */

    private int code;
    private String msg;
    private int time;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * version : 1.2
         * download : http://img.oceanscm.com/andriod/driver.apk
         */

        private String version;
        private String download;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }
    }
}
