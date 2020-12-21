package com.ocean.driver.entity;

import java.util.List;

/**
 * Created by James on 2020/11/9.
 */
public class OperaDetails {

        /**
         * os_id : 67
         * s_type : 3
         * goods_jnum : 0
         * goods_num : 0
         * total_weight : 0.00
         * total_volume : 0.00
         * start_city : 上海/苏州
         * end_city : 北京/天津
         * status : 4
         * d_reject_time : 2020-11-06 15:41
         * d_reject : 123456
         * pickup_address : [{"name":"江苏省南京物流有限公司","contract_name":"2345678","contract_tel":"13511111111","info":"12324","arrive_time":null}]
         * delivery_address : [{"name":"大洋33","contract_name":"qinfeng","contract_tel":"15200000000","info":"北京北京市东城区123456789","arrive_time":"2010-10-10 10:00"},{"name":"大洋33","contract_name":"qinfeng","contract_tel":"15200000000","info":"北京北京市东城区123456789","arrive_time":"2010-10-10 10:00"}]
         */

        private String os_id;
        private String osd_id;
        private String o_id;

    public String getOsd_id() {
        return osd_id;
    }

    public void setOsd_id(String osd_id) {
        this.osd_id = osd_id;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    private String s_type;
        private String goods_jnum;
        private String goods_num;
        private String total_weight;
        private String total_volume;
        private String start_city;
        private String end_city;
        private String status;
        private String d_reject_time;
        private String d_reject;
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
        private List<PickupAddressBean> pickup_address;
        private List<DeliveryAddressBean> delivery_address;

        public String getOs_id() {
            return os_id;
        }

        public void setOs_id(String os_id) {
            this.os_id = os_id;
        }

        public String getS_type() {
            return s_type;
        }

        public void setS_type(String s_type) {
            this.s_type = s_type;
        }

        public String getGoods_jnum() {
            return goods_jnum;
        }

        public void setGoods_jnum(String goods_jnum) {
            this.goods_jnum = goods_jnum;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public List<PickupAddressBean> getPickup_address() {
            return pickup_address;
        }

        public void setPickup_address(List<PickupAddressBean> pickup_address) {
            this.pickup_address = pickup_address;
        }

        public List<DeliveryAddressBean> getDelivery_address() {
            return delivery_address;
        }

        public void setDelivery_address(List<DeliveryAddressBean> delivery_address) {
            this.delivery_address = delivery_address;
        }

        public static class PickupAddressBean {
            /**
             * name : 江苏省南京物流有限公司
             * contract_name : 2345678
             * contract_tel : 13511111111
             * info : 12324
             * arrive_time : null
             */

            private String name;
            private String contract_name;
            private String contract_tel;
            private String info;
            private Object arrive_time;

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

            public Object getArrive_time() {
                return arrive_time;
            }

            public void setArrive_time(Object arrive_time) {
                this.arrive_time = arrive_time;
            }
        }

        public static class DeliveryAddressBean {
            /**
             * name : 大洋33
             * contract_name : qinfeng
             * contract_tel : 15200000000
             * info : 北京北京市东城区123456789
             * arrive_time : 2010-10-10 10:00
             */

            private String name;
            private String contract_name;
            private String contract_tel;
            private String info;
            private String arrive_time;

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

            public String getArrive_time() {
                return arrive_time;
            }

            public void setArrive_time(String arrive_time) {
                this.arrive_time = arrive_time;
            }
        }

}
