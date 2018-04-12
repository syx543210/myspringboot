package cn.bdqn.managerweb.entity;

import java.io.Serializable;

/**
 * @author syx
 * @date 17:35 2018-3-27
 * @description
 */
public class User implements Serializable {
    private Long id;
    private String name;
    private String password;
    private Integer status;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
