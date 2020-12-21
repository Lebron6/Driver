package com.ocean.driver.entity;

/**
 * Created by James on 2020/8/18.
 */
public class VehicleStatus {

        /**
         * vehicle : {"vehicle_id":"14","num":"苏E45263","max_weight":"8.00","max_volume":"8.00","status":"2","remain_weight":"4","remain_volume":"4"}
         */

        private VehicleBean vehicle;

        public VehicleBean getVehicle() {
            return vehicle;
        }

        public void setVehicle(VehicleBean vehicle) {
            this.vehicle = vehicle;
        }

        public static class VehicleBean {
            /**
             * vehicle_id : 14
             * num : 苏E45263
             * max_weight : 8.00
             * max_volume : 8.00
             * status : 2
             * remain_weight : 4
             * remain_volume : 4
             */

            private String vehicle_id;
            private String num;
            private String max_weight;
            private String max_volume;
            private String status;
            private String remain_weight;
            private String remain_volume;

            public String getVehicle_id() {
                return vehicle_id;
            }

            public void setVehicle_id(String vehicle_id) {
                this.vehicle_id = vehicle_id;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getMax_weight() {
                return max_weight;
            }

            public void setMax_weight(String max_weight) {
                this.max_weight = max_weight;
            }

            public String getMax_volume() {
                return max_volume;
            }

            public void setMax_volume(String max_volume) {
                this.max_volume = max_volume;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRemain_weight() {
                return remain_weight;
            }

            public void setRemain_weight(String remain_weight) {
                this.remain_weight = remain_weight;
            }

            public String getRemain_volume() {
                return remain_volume;
            }

            public void setRemain_volume(String remain_volume) {
                this.remain_volume = remain_volume;
            }
        }

}
