package com.ocean.driver.entity;

import java.util.List;

/**
 * Created by James on 2020/9/27.
 */
public class BillData {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1602228636
     * data : {"current_page":"1","per_page":10,"has_more":false,"total":"5","pageall":1,"list":[{"dpv_id":"33","sdl_name":"常州三人禾物流有限公司","dp_num":"T202010097238","start_time":"2020/10/01 09:00","end_time":"2020/10/01 10:00","start_address":{"w_id":"23","name":"江苏省南京仓库","contract_name":"南京联系人","contract_tel":"18900000000","info":"江苏省南京市雨花台区物流路1号","lng":"118.78544536406","lat":"31.997858805466"},"end_address":{"sa_id":"50","name":"北京物流运输有限公司","contract_name":"北京联系人","contract_tel":"15200000000","info":"北京北京市东城区收货地址1号","lng":"116.42240097766","lat":"39.934827272396"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"},{"dpv_id":"31","sdl_name":"常州三人禾物流有限公司","dp_num":"T202009255490","start_time":"2020/09/10 00:00","end_time":"2020/09/25 11:24","start_address":{"w_id":"34","name":"江苏常州仓","contract_name":"常州联系人","contract_tel":"15261566656","info":"江苏省常州市武进区物流路1号","lng":"119.90527036279","lat":"31.788355796771"},"end_address":{"sa_id":"40","name":"北京物流运输有限公司","contract_name":"北京联系人","contract_tel":"15200000000","info":"北京北京市丰台区企业中心1号","lng":"116.29240188731","lat":"39.864937197557"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"},{"dpv_id":"26","sdl_name":"常州三人禾物流有限公司","dp_num":"T202009247402","start_time":"2020/09/03 00:00","end_time":"2020/09/05 00:00","start_address":{"w_id":"23","name":"江苏省南京仓库","contract_name":"南京联系人","contract_tel":"18900000000","info":"江苏省南京市雨花台区物流路1号","lng":"118.78544536406","lat":"31.997858805466"},"end_address":{"sa_id":"37","name":"大洋","contract_name":"大洋联系人","contract_tel":"15261566656","info":"江苏省苏州市虎丘区经贸路1号","lng":"120.57847178571","lat":"31.301932876554"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"},{"dpv_id":"25","sdl_name":"常州三人禾物流有限公司","dp_num":"T202009252661","start_time":"2020/09/02 00:00","end_time":"2020/09/03 00:00","start_address":{"w_id":"23","name":"江苏省南京仓库","contract_name":"南京联系人","contract_tel":"18900000000","info":"江苏省南京市雨花台区物流路1号","lng":"118.78544536406","lat":"31.997858805466"},"end_address":{"sa_id":"43","name":"北京物流运输有限公司","contract_name":"去微软推与","contract_tel":"15200000000","info":"山东省济南市历下区1234567o","lng":"117.0825673139","lat":"36.672162950043"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"},{"dpv_id":"23","sdl_name":"常州三人禾物流有限公司","dp_num":"T202009251720","start_time":"2020/09/08 00:00","end_time":"2020/09/10 00:00","start_address":{"w_id":"23","name":"江苏省南京仓库","contract_name":"南京联系人","contract_tel":"18900000000","info":"江苏省南京市雨花台区物流路1号","lng":"118.78544536406","lat":"31.997858805466"},"end_address":{"sa_id":"44","name":"北京物流运输有限公司","contract_name":"12345678i","contract_tel":"15200000000","info":"江苏省宿迁市宿豫区宿迁实验小学","lng":"118.34588354751","lat":"33.950635310194"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"}]}
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
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 5
         * pageall : 1
         * list : [{"dpv_id":"33","sdl_name":"常州三人禾物流有限公司","dp_num":"T202010097238","start_time":"2020/10/01 09:00","end_time":"2020/10/01 10:00","start_address":{"w_id":"23","name":"江苏省南京仓库","contract_name":"南京联系人","contract_tel":"18900000000","info":"江苏省南京市雨花台区物流路1号","lng":"118.78544536406","lat":"31.997858805466"},"end_address":{"sa_id":"50","name":"北京物流运输有限公司","contract_name":"北京联系人","contract_tel":"15200000000","info":"北京北京市东城区收货地址1号","lng":"116.42240097766","lat":"39.934827272396"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"},{"dpv_id":"31","sdl_name":"常州三人禾物流有限公司","dp_num":"T202009255490","start_time":"2020/09/10 00:00","end_time":"2020/09/25 11:24","start_address":{"w_id":"34","name":"江苏常州仓","contract_name":"常州联系人","contract_tel":"15261566656","info":"江苏省常州市武进区物流路1号","lng":"119.90527036279","lat":"31.788355796771"},"end_address":{"sa_id":"40","name":"北京物流运输有限公司","contract_name":"北京联系人","contract_tel":"15200000000","info":"北京北京市丰台区企业中心1号","lng":"116.29240188731","lat":"39.864937197557"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"},{"dpv_id":"26","sdl_name":"常州三人禾物流有限公司","dp_num":"T202009247402","start_time":"2020/09/03 00:00","end_time":"2020/09/05 00:00","start_address":{"w_id":"23","name":"江苏省南京仓库","contract_name":"南京联系人","contract_tel":"18900000000","info":"江苏省南京市雨花台区物流路1号","lng":"118.78544536406","lat":"31.997858805466"},"end_address":{"sa_id":"37","name":"大洋","contract_name":"大洋联系人","contract_tel":"15261566656","info":"江苏省苏州市虎丘区经贸路1号","lng":"120.57847178571","lat":"31.301932876554"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"},{"dpv_id":"25","sdl_name":"常州三人禾物流有限公司","dp_num":"T202009252661","start_time":"2020/09/02 00:00","end_time":"2020/09/03 00:00","start_address":{"w_id":"23","name":"江苏省南京仓库","contract_name":"南京联系人","contract_tel":"18900000000","info":"江苏省南京市雨花台区物流路1号","lng":"118.78544536406","lat":"31.997858805466"},"end_address":{"sa_id":"43","name":"北京物流运输有限公司","contract_name":"去微软推与","contract_tel":"15200000000","info":"山东省济南市历下区1234567o","lng":"117.0825673139","lat":"36.672162950043"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"},{"dpv_id":"23","sdl_name":"常州三人禾物流有限公司","dp_num":"T202009251720","start_time":"2020/09/08 00:00","end_time":"2020/09/10 00:00","start_address":{"w_id":"23","name":"江苏省南京仓库","contract_name":"南京联系人","contract_tel":"18900000000","info":"江苏省南京市雨花台区物流路1号","lng":"118.78544536406","lat":"31.997858805466"},"end_address":{"sa_id":"44","name":"北京物流运输有限公司","contract_name":"12345678i","contract_tel":"15200000000","info":"江苏省宿迁市宿豫区宿迁实验小学","lng":"118.34588354751","lat":"33.950635310194"},"finish_time":"1970/01/01 08:00","reject_reason":"","reject_time":"1970/01/01 08:00","status":"1"}]
         */

        private String current_page;
        private int per_page;
        private boolean has_more;
        private String total;
        private int pageall;
        private List<ListBean> list;

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPageall() {
            return pageall;
        }

        public void setPageall(int pageall) {
            this.pageall = pageall;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * dpv_id : 33
             * sdl_name : 常州三人禾物流有限公司
             * dp_num : T202010097238
             * start_time : 2020/10/01 09:00
             * end_time : 2020/10/01 10:00
             * start_address : {"w_id":"23","name":"江苏省南京仓库","contract_name":"南京联系人","contract_tel":"18900000000","info":"江苏省南京市雨花台区物流路1号","lng":"118.78544536406","lat":"31.997858805466"}
             * end_address : {"sa_id":"50","name":"北京物流运输有限公司","contract_name":"北京联系人","contract_tel":"15200000000","info":"北京北京市东城区收货地址1号","lng":"116.42240097766","lat":"39.934827272396"}
             * finish_time : 1970/01/01 08:00
             * reject_reason :
             * reject_time : 1970/01/01 08:00
             * status : 1
             */

            private String dpv_id;
            private String sdl_name;
            private String dp_num;
            private String start_time;
            private String end_time;
            private StartAddressBean start_address;
            private EndAddressBean end_address;
            private String finish_time;
            private String reject_reason;
            private String reject_time;
            private String status;

            public String getDpv_id() {
                return dpv_id;
            }

            public void setDpv_id(String dpv_id) {
                this.dpv_id = dpv_id;
            }

            public String getSdl_name() {
                return sdl_name;
            }

            public void setSdl_name(String sdl_name) {
                this.sdl_name = sdl_name;
            }

            public String getDp_num() {
                return dp_num;
            }

            public void setDp_num(String dp_num) {
                this.dp_num = dp_num;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public StartAddressBean getStart_address() {
                return start_address;
            }

            public void setStart_address(StartAddressBean start_address) {
                this.start_address = start_address;
            }

            public EndAddressBean getEnd_address() {
                return end_address;
            }

            public void setEnd_address(EndAddressBean end_address) {
                this.end_address = end_address;
            }

            public String getFinish_time() {
                return finish_time;
            }

            public void setFinish_time(String finish_time) {
                this.finish_time = finish_time;
            }

            public String getReject_reason() {
                return reject_reason;
            }

            public void setReject_reason(String reject_reason) {
                this.reject_reason = reject_reason;
            }

            public String getReject_time() {
                return reject_time;
            }

            public void setReject_time(String reject_time) {
                this.reject_time = reject_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public static class StartAddressBean {
                /**
                 * w_id : 23
                 * name : 江苏省南京仓库
                 * contract_name : 南京联系人
                 * contract_tel : 18900000000
                 * info : 江苏省南京市雨花台区物流路1号
                 * lng : 118.78544536406
                 * lat : 31.997858805466
                 */

                private String w_id;
                private String name;
                private String contract_name;
                private String contract_tel;
                private String info;
                private String lng;
                private String lat;

                public String getW_id() {
                    return w_id;
                }

                public void setW_id(String w_id) {
                    this.w_id = w_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getContract_name() {
                    return contract_name;
                }

                public void setContract_name(String contract_name) {
                    this.contract_name = contract_name;
                }

                public String getContract_tel() {
                    return contract_tel;
                }

                public void setContract_tel(String contract_tel) {
                    this.contract_tel = contract_tel;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }
            }

            public static class EndAddressBean {
                /**
                 * sa_id : 50
                 * name : 北京物流运输有限公司
                 * contract_name : 北京联系人
                 * contract_tel : 15200000000
                 * info : 北京北京市东城区收货地址1号
                 * lng : 116.42240097766
                 * lat : 39.934827272396
                 */

                private String sa_id;
                private String name;
                private String contract_name;
                private String contract_tel;
                private String info;
                private String lng;
                private String lat;

                public String getSa_id() {
                    return sa_id;
                }

                public void setSa_id(String sa_id) {
                    this.sa_id = sa_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getContract_name() {
                    return contract_name;
                }

                public void setContract_name(String contract_name) {
                    this.contract_name = contract_name;
                }

                public String getContract_tel() {
                    return contract_tel;
                }

                public void setContract_tel(String contract_tel) {
                    this.contract_tel = contract_tel;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }
            }
        }
    }
}
