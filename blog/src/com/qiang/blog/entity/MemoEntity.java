package com.qiang.blog.entity;

import java.io.Serializable;

public class MemoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int memo_type = 1;
    private String memo_createtime;
    private String memo_content;
    private boolean memo_overdue;
    private String memo_date;
    private int memo_action;
    private int memo_id;
    
    
    public int getMemo_id() {
        return memo_id;
    }
    public void setMemo_id(int memo_id) {
        this.memo_id = memo_id;
    }
    public int getMemo_action() {
        return memo_action;
    }
    public void setMemo_action(int memo_action) {
        this.memo_action = memo_action;
    }
    public int getMemo_type() {
        return memo_type;
    }
    public void setMemo_type(int memo_type) {
        this.memo_type = memo_type;
    }
    public String getMemo_createtime() {
        return memo_createtime;
    }
    public void setMemo_createtime(String memo_createtime) {
        this.memo_createtime = memo_createtime;
    }
    public String getMemo_content() {
        return memo_content;
    }
    public void setMemo_content(String memo_content) {
        this.memo_content = memo_content;
    }
    public boolean isMemo_overdue() {
        return memo_overdue;
    }
    public void setMemo_overdue(boolean memo_overdue) {
        this.memo_overdue = memo_overdue;
    }
    public String getMemo_date() {
        return memo_date;
    }
    public void setMemo_date(String memo_date) {
        this.memo_date = memo_date;
    }

}
