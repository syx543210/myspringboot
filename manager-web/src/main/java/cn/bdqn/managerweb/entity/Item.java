package cn.bdqn.managerweb.entity;

import java.io.Serializable;

/**
 * @author syx
 * @date 17:35 2018-3-27
 * @description
 */
public class Item implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private Integer num;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
