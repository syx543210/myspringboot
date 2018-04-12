package cn.bdqn.center.service.impl;

import cn.bdqn.center.entity.User;
import cn.bdqn.center.repository.UserRepository;
import cn.bdqn.center.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author syx
 * @date 15:08 2018-3-26
 * @description
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> showUser(Integer page, Integer length) {
        Pageable pageable=new PageRequest(page,length);
        return userRepository.showUser(pageable);
    }

    @Override
    public int deleteUser(Long userId) {
        return userRepository.deleteUser(userId);
    }

    @Override
    public List<User> selectAll() {
        return userRepository.selectAll();
    }
}
