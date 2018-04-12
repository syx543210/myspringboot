package cn.bdqn.center.service;

import cn.bdqn.center.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author syx
 * @date 15:08 2018-3-26
 * @description
 */
public interface UserService {

    Page<User> showUser(Integer page, Integer length);

    int deleteUser(Long userId);

    List<User> selectAll();
}
