package com.shop.user.vo;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class User {
    private int uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String sex;
    private String birth;
    private String phone;
    private String addr;
    private Integer state;  // 0: 未激活, 1: 已激活
    private String code;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return uid == user.uid &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(birth, user.birth) &&
                Objects.equals(addr, user.addr) &&
                Objects.equals(state, user.state) &&
                Objects.equals(code, user.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, username, password, name, email, sex, birth, addr, state, code);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", addr='" + addr + '\'' +
                ", state=" + state +
                ", code='" + code + '\'' +
                '}';
    }
}
