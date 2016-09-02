package com.zzl.cn.bean;

import java.io.Serializable;

/**
 * Created by zhangzl
 * desc:
 * date: 16-9-2.
 */

public class UserBean implements Serializable {
    private String name;
    private String phone;
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
