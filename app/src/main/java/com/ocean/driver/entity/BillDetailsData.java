package com.ocean.driver.entity;

/**
 * Created by James on 2020/9/28.
 */
public class BillDetailsData {

        /**
         * sdl_name : 大洋1
         * sdl_mobile : 0354-84615564
         * dp_num : T122
         * start_address : {"name":"大锅饭1号","contract_name":"联系人","contract_tel":"18765856968","info":"北京北京市东城区故宫西路230号","lng":"116.42240097766","lat":"39.934827272396"}
         * end_address : {"name":"卫林盛","contract_name":"灭霸哈哈","contract_tel":"15296713935","info":"内蒙古自治区鄂尔多斯市杭锦旗回龙观1231","lng":"108.74241293879","lat":"39.838815768815"}
         * vehicle : 苏222
         * range : 500
         */

        private String sdl_name;
        private String sdl_mobile;
        private String dp_num;
        private StartAddressBean start_address;
        private EndAddressBean end_address;
        private String vehicle;
        private int range;

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

        public String getVehicle() {
            return vehicle;
        }

        public void setVehicle(String vehicle) {
            this.vehicle = vehicle;
        }

        public int getRange() {
            return range;
        }

        public void setRange(int range) {
            this.range = range;
        }

        public static class StartAddressBean {
            /**
             * name : 大锅饭1号
             * contract_name : 联系人
             * contract_tel : 18765856968
             * info : 北京北京市东城区故宫西路230号
             * lng : 116.42240097766
             * lat : 39.934827272396
             */

            private String name;
            private String contract_name;
            private String contract_tel;
            private String info;
            private String lng;
            private String lat;

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
             * name : 卫林盛
             * contract_name : 灭霸哈哈
             * contract_tel : 15296713935
             * info : 内蒙古自治区鄂尔多斯市杭锦旗回龙观1231
             * lng : 108.74241293879
             * lat : 39.838815768815
             */

            private String name;
            private String contract_name;
            private String contract_tel;
            private String info;
            private String lng;
            private String lat;

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
