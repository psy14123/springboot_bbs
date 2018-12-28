package cn.psy.homework11.pojo;

import java.io.Serializable;

/**
 * 用户的用户名，电话，密码
 */
public class User implements Serializable {
    private String name;//用户昵称

    private String password;//用户密码

    private String tel;//用户电话

    private int id;//用户id

    private int role_id;//角色的id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", id=" + id +
                ", role_id=" + role_id +
                '}';
    }
}
