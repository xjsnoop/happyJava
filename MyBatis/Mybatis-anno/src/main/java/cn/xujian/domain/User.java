package cn.xujian.domain;

import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;

//    描述当前用户拥有的角色
    private List<Role> roleList;
//    描述当前用户具有的订单
    private List<Orders> ordersList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Orders> getOrder() {
        return ordersList;
    }

    public void setOrder(List<Orders> order) {
        this.ordersList = ordersList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleList=" + roleList +
                ", ordersList=" + ordersList +
                '}';
    }
}
