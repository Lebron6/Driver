package com.ocean.driver.entity;

import java.util.List;

/**
 * Created by James on 2020/11/20.
 */
public class OperaSetoutList {


        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 1
         * pageall : 1
         * list : [{"osd_id":"26","os_id":"195","o_id":"83","s_type":"2","s_status":"3","goods_jnum":"3","total_weight":"1940.00","total_volume":"15.00","start_city":"苏州市","end_city":"无锡市","pk_name":"通用型Ⅰ等","pickup_address":"江苏省苏州市吴中区金猫路200号等","delivery_address":"江苏省无锡市滨湖区十里明珠堤100号等","arrival_time":"2020-11-13 00:00","d_reject_time":"1970-01-01 08:00","d_reject":"","has_prev":false,"has_next":false,"is_takeover":"1"}]
         */

        private int current_page;
        private int per_page;
        private boolean has_more;
        private int total;
        private int pageall;
        private List<ListBean> list;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
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

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
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
             * osd_id : 26
             * os_id : 195
             * o_id : 83
             * s_type : 2
             * s_status : 3
             * goods_jnum : 3
             * total_weight : 1940.00
             * total_volume : 15.00
             * start_city : 苏州市
             * end_city : 无锡市
             * pk_name : 通用型Ⅰ等
             * pickup_address : 江苏省苏州市吴中区金猫路200号等
             * delivery_address : 江苏省无锡市滨湖区十里明珠堤100号等
             * arrival_time : 2020-11-13 00:00
             * d_reject_time : 1970-01-01 08:00
             * d_reject :
             * has_prev : false
             * has_next : false
             * is_takeover : 1
             */

            private String osd_id;
            private String os_id;
            private String o_id;
            private String s_type;
            private String s_status;
            private String goods_jnum;
            private String total_weight;
            private String total_volume;
            private String start_city;
            private String end_city;
            private String pk_name;
            private String pickup_address;
            private String delivery_address;
            private String arrival_time;
            private String d_reject_time;
            private String d_reject;
            private boolean has_prev;
            private boolean has_next;
            private String is_takeover;
            private String now_goods_jnum;
            private String now_goods_num;

            public String getNow_goods_jnum() {
                return now_goods_jnum;
            }

            public void setNow_goods_jnum(String now_goods_jnum) {
                this.now_goods_jnum = now_goods_jnum;
            }

            public String getNow_goods_num() {
                return now_goods_num;
            }

            public void setNow_goods_num(String now_goods_num) {
                this.now_goods_num = now_goods_num;
            }
            public String getOsd_id() {
                return osd_id;
            }

            public void setOsd_id(String osd_id) {
                this.osd_id = osd_id;
            }

            public String getOs_id() {
                return os_id;
            }

            public void setOs_id(String os_id) {
                this.os_id = os_id;
            }

            public String getO_id() {
                return o_id;
            }

            public void setO_id(String o_id) {
                this.o_id = o_id;
            }

            public String getS_type() {
                return s_type;
            }

            public void setS_type(String s_type) {
                this.s_type = s_type;
            }

            public String getS_status() {
                return s_status;
            }

            public void setS_status(String s_status) {
                this.s_status = s_status;
            }

            public String getGoods_jnum() {
                return goods_jnum;
            }

            public void setGoods_jnum(String goods_jnum) {
                this.goods_jnum = goods_jnum;
            }

            public String getTotal_weight() {
                return total_weight;
            }

            public void setTotal_weight(String total_weight) {
                this.total_weight = total_weight;
            }

            public String getTotal_volume() {
                return total_volume;
            }

            public void setTotal_volume(String total_volume) {
                this.total_volume = total_volume;
            }

            public String getStart_city() {
                return start_city;
            }

            public void setStart_city(String start_city) {
                this.start_city = start_city;
            }

            public String getEnd_city() {
                return end_city;
            }

            public void setEnd_city(String end_city) {
                this.end_city = end_city;
            }

            public String getPk_name() {
                return pk_name;
            }

            public void setPk_name(String pk_name) {
                this.pk_name = pk_name;
            }

            public String getPickup_address() {
                return pickup_address;
            }

            public void setPickup_address(String pickup_address) {
                this.pickup_address = pickup_address;
            }

            public String getDelivery_address() {
                return delivery_address;
            }

            public void setDelivery_address(String delivery_address) {
                this.delivery_address = delivery_address;
            }

            public String getArrival_time() {
                return arrival_time;
            }

            public void setArrival_time(String arrival_time) {
                this.arrival_time = arrival_time;
            }

            public String getD_reject_time() {
                return d_reject_time;
            }

            public void setD_reject_time(String d_reject_time) {
                this.d_reject_time = d_reject_time;
            }

            public String getD_reject() {
                return d_reject;
            }

            public void setD_reject(String d_reject) {
                this.d_reject = d_reject;
            }

            public boolean isHas_prev() {
                return has_prev;
            }

            public void setHas_prev(boolean has_prev) {
                this.has_prev = has_prev;
            }

            public boolean isHas_next() {
                return has_next;
            }

            public void setHas_next(boolean has_next) {
                this.has_next = has_next;
            }

            public String getIs_takeover() {
                return is_takeover;
            }

            public void setIs_takeover(String is_takeover) {
                this.is_takeover = is_takeover;
            }
        }

}
