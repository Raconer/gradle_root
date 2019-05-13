package com.multi.gradle.module.web.model;

/**
 * Created by dhokim on 2019-05-10
 */
public class TestModel {
    private int idx;
    private String title;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                '}';
    }
}
