package com.ocean.driver.entity;

/**
 * Created by James on 2020/12/3.
 */
public class OnWay {

        /**
         * on_way : false
         * location_time : 61
         * location_distance : 1001
         */

        private boolean on_way;
        private String location_time;
        private String location_distance;

        public boolean isOn_way() {
            return on_way;
        }

        public void setOn_way(boolean on_way) {
            this.on_way = on_way;
        }

        public String getLocation_time() {
            return location_time;
        }

        public void setLocation_time(String location_time) {
            this.location_time = location_time;
        }

        public String getLocation_distance() {
            return location_distance;
        }

        public void setLocation_distance(String location_distance) {
            this.location_distance = location_distance;
        }

}
