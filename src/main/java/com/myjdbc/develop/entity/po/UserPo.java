package com.myjdbc.develop.entity.po;

import com.myjdbc.datagrid.annotation.DatagridColumn;

public class UserPo extends UserPo3 {

    @DatagridColumn(title = "姓名")
    private String name;

    @DatagridColumn(title = "单位")
    private String dept;

    @DatagridColumn(title = "电话")
    private String ipone;

    @DatagridColumn(title = "工作岗位")
    private String job;

    @DatagridColumn(title = "级别")
    private String level;

    @DatagridColumn(title = "迟到")
    private String chidao;

    @DatagridColumn(title = "早退")
    private String zaotui;

    @DatagridColumn(title = "事假")
    private String shijia;

    @DatagridColumn(title = "年假")
    private String nianjia;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getIpone() {
        return ipone;
    }

    public void setIpone(String ipone) {
        this.ipone = ipone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getChidao() {
        return chidao;
    }

    public void setChidao(String chidao) {
        this.chidao = chidao;
    }

    public String getZaotui() {
        return zaotui;
    }

    public void setZaotui(String zaotui) {
        this.zaotui = zaotui;
    }

    public String getShijia() {
        return shijia;
    }

    public void setShijia(String shijia) {
        this.shijia = shijia;
    }

    public String getNianjia() {
        return nianjia;
    }

    public void setNianjia(String nianjia) {
        this.nianjia = nianjia;
    }
}
