package cn.bdqn.managerweb.service.impl;

import cn.bdqn.managerweb.entity.User;
import cn.bdqn.managerweb.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author syx
 * @date 14:19 2018-3-26
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String showUser(Integer draw, Integer start, Integer length) {
        String url = centerUrl + "user/showUser/{draw}/{start}/{length}";
        Map<String,Object> map=new HashMap<>();
        map.put("draw",draw);
        map.put("start",start);
        map.put("length",length);
        String json = restTemplate.getForObject(url, String.class, map);
        System.out.println(json);
        return json;
    }

    @Override
    public String deleteUser(Long userId) {
        String url = centerUrl + "user/deleteUser/{userId}";
        Integer updateRows = restTemplate.getForObject(url, Integer.class, userId);
        if(updateRows>0){
            return "1";
        }else {
            return "2";
        }
    }

    @Override
    public List<User> selectAll() {
        String url = centerUrl + "user/selectAll";
        String json = restTemplate.getForObject(url, String.class);
        List<User> userList = JSONObject.parseArray(json, User.class);
        return userList;
    }
}
