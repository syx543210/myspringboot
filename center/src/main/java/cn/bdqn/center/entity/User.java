package cn.bdqn.center.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author syx
 * @date 14:52 2018-3-23
 * @description
 */

@Entity
@Table(name = "mb_user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Long id;
    @Column(name = "name", length = 32)
    private String name;
    @Column(name = "password", length = 32)
    private String password;
    @Column(name = "status")
    private Integer status;

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
