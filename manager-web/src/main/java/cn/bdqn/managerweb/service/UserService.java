package cn.bdqn.managerweb.service;

import cn.bdqn.managerweb.entity.User;

import java.util.List;

/**
 * @author syx
 * @date 14:19 2018-3-26
 * @description
 */
public interface UserService {
    String showUser(Integer draw, Integer start, Integer length);

    String deleteUser(Long userId);

    List<User> selectAll();
}
