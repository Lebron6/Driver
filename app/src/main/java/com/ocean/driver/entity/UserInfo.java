package com.ocean.driver.entity;

/**
 * Created by James on 2020/8/17.
 */
public class UserInfo {

        /**
         * driver : {"name":"李欢","phone":"18168724682","headimg":"http://img.oceanscm.com/driver/headimg/2020081498354504251985.png"}
         * notice :
         * service_phone : 400-0000-0000
         */

        private DriverBean driver;
        private String notice;
        private String service_phone;

        public DriverBean getDriver() {
            return driver;
        }

        public void setDriver(DriverBean driver) {
            this.driver = driver;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getService_phone() {
            return service_phone;
        }

        public void setService_phone(String service_phone) {
            this.service_phone = service_phone;
        }

        public static class DriverBean {
            /**
             * name : 李欢
             * phone : 18168724682
             * headimg : http://img.oceanscm.com/driver/headimg/2020081498354504251985.png
             */

            private String name;
            private String phone;
            private String headimg;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }
        }
}
