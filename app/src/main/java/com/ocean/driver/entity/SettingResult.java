package com.ocean.driver.entity;

import java.util.List;

/**
 * Created by James on 2020/8/17.
 */
public class SettingResult {



        private DriverBean driver;
        private boolean has_password;
        private String phone;
        private List<LicenseTypeBean> license_type;

        public DriverBean getDriver() {
            return driver;
        }

        public void setDriver(DriverBean driver) {
            this.driver = driver;
        }

        public boolean isHas_password() {
            return has_password;
        }

        public void setHas_password(boolean has_password) {
            this.has_password = has_password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public List<LicenseTypeBean> getLicense_type() {
            return license_type;
        }

        public void setLicense_type(List<LicenseTypeBean> license_type) {
            this.license_type = license_type;
        }

        public static class DriverBean {
            /**
             * name : 李欢
             * id_card : 320586194603164321
             * type : A1
             * license_num : 1556213546789
             * tel_name : lihuan
             * tel_num : 13625462365
             */

            private String name;
            private String id_card;
            private String type;
            private String license_num;
            private String tel_name;
            private String tel_num;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLicense_num() {
                return license_num;
            }

            public void setLicense_num(String license_num) {
                this.license_num = license_num;
            }

            public String getTel_name() {
                return tel_name;
            }

            public void setTel_name(String tel_name) {
                this.tel_name = tel_name;
            }

            public String getTel_num() {
                return tel_num;
            }

            public void setTel_num(String tel_num) {
                this.tel_num = tel_num;
            }
        }

        public static class LicenseTypeBean {
            /**
             * id : 1
             * name : A1
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

}
