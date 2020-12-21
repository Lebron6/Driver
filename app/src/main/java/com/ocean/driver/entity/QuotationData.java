package com.ocean.driver.entity;

import java.util.List;

/**
 * Created by James on 2020/9/23.
 */
public class QuotationData {


        private List<String> header;
        private List<List<String>> list;

        public List<String> getHeader() {
            return header;
        }

        public void setHeader(List<String> header) {
            this.header = header;
        }

        public List<List<String>> getList() {
            return list;
        }

        public void setList(List<List<String>> list) {
            this.list = list;
        }
    }

