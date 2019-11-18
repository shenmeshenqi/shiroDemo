package com.zzh.shirotest.demo.domain;

import java.io.Serializable;

public class Permission implements Serializable {
    @Override
    public String toString() {
        return "Permission{" +
                "perid=" + perid +
                ", pername='" + pername + '\'' +
                ", percode='" + percode + '\'' +
                '}';
    }

    private Integer perid;

    private String pername;

    private String percode;

    private static final long serialVersionUID = 1L;

    public Integer getPerid() {
        return perid;
    }

    public void setPerid(Integer perid) {
        this.perid = perid;
    }

    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername == null ? null : pername.trim();
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode == null ? null : percode.trim();
    }
}