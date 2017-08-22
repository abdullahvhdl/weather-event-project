package com.test.weatherproject.controller;

import java.util.Collections;
import java.util.List;

public class ResponseListObject {

    private int code;
    private int rows;

    private List<?> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<?> getList() {
        return Collections.unmodifiableList(list);
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
