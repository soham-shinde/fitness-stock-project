package com.fitsta.fitsta.DTO;

public class ContactUsRequest {
    private String name;
    private String contactno;
    private String msg;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContactno() {
        return contactno;
    }
    public void setContactno(String contactno) {
        this.contactno = contactno;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
