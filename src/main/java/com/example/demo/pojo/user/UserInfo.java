package com.example.demo.pojo.user;

import java.util.List;

public class UserInfo {
    private String avatar;
    private String introduction;
    private String name;
    private List<String> roles;

    public UserInfo(String avatar, String introduction, String name, List<String> roles) {
        this.avatar = avatar;
        this.introduction = introduction;
        this.name = name;
        this.roles = roles;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "avatar='" + avatar + '\'' +
                ", introduction='" + introduction + '\'' +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
