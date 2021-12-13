package com.siamsoft.customeradd;



//-------------only for three field-----
public class Customer {

    private int 	c_id;
    private String c_fullname, c_mobile;

    public Customer(int c_id, String c_fullname, String c_mobile) {
        this.c_id = c_id;
        this.c_fullname = c_fullname;
        this.c_mobile = c_mobile;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_fullname() {
        return c_fullname;
    }

    public void setC_fullname(String c_fullname) {
        this.c_fullname = c_fullname;
    }

    public String getC_mobile() {
        return c_mobile;
    }

    public void setC_mobile(String c_mobile) {
        this.c_mobile = c_mobile;
    }

}



