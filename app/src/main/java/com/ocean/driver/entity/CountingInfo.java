package com.ocean.driver.entity;

import java.util.List;

/**
 * Created by James on 2020/10/12.
 */
public class CountingInfo {

        /**
         * sdl_name : 大洋1
         * sdl_mobile : 0354-84615564
         * dp_num : T122
         * vehicle : 苏222
         * goods : [{"g_id":"1","pro_num":"HW202008111847","name":"冰箱零一21","pack_type":"周转箱","take_num":"34","weight":34500,"volume":8,"num":"100","jnum":"1"},{"g_id":"2","pro_num":"HW202008119156","name":"电池零一","pack_type":"围板箱","take_num":"111","weight":80,"volume":8,"num":"80","jnum":"1"}]
         */

        private String sdl_name;
        private String sdl_mobile;
        private String dp_num;
        private String vehicle;
        private List<GoodsBean> goods;

        public String getSdl_name() {
            return sdl_name;
        }

        public void setSdl_name(String sdl_name) {
            this.sdl_name = sdl_name;
        }

        public String getSdl_mobile() {
            return sdl_mobile;
        }

        public void setSdl_mobile(String sdl_mobile) {
            this.sdl_mobile = sdl_mobile;
        }

        public String getDp_num() {
            return dp_num;
        }

        public void setDp_num(String dp_num) {
            this.dp_num = dp_num;
        }

        public String getVehicle() {
            return vehicle;
        }

        public void setVehicle(String vehicle) {
            this.vehicle = vehicle;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * g_id : 1
             * pro_num : HW202008111847
             * name : 冰箱零一21
             * pack_type : 周转箱
             * take_num : 34
             * weight : 34500
             * volume : 8
             * num : 100
             * jnum : 1
             */

            private String g_id;
            private String pro_num;
            private String name;
            private String pack_type;
            private String take_num;
            private String weight;
            private String volume;
            private String num;
            private String jnum;
            private int type;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getG_id() {
                return g_id;
            }

            public void setG_id(String g_id) {
                this.g_id = g_id;
            }

            public String getPro_num() {
                return pro_num;
            }

            public void setPro_num(String pro_num) {
                this.pro_num = pro_num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPack_type() {
                return pack_type;
            }

            public void setPack_type(String pack_type) {
                this.pack_type = pack_type;
            }

            public String getTake_num() {
                return take_num;
            }

            public void setTake_num(String take_num) {
                this.take_num = take_num;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getJnum() {
                return jnum;
            }

            public void setJnum(String jnum) {
                this.jnum = jnum;
            }
        }

}
