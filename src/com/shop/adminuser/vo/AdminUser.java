package com.shop.adminuser.vo;

import java.util.Objects;

/**
 * 后台用户对象类
 */
public class AdminUser {
    private int uid;
    private String username;
    private String password;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminUser adminuser = (AdminUser) o;
        return uid == adminuser.uid &&
                Objects.equals(username, adminuser.username) &&
                Objects.equals(password, adminuser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, username, password);
    }
}
