package cn.bdqn.center.controller;

import cn.bdqn.center.dataTable.DatatablesView;
import cn.bdqn.center.entity.User;
import cn.bdqn.center.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author syx
 * @date 14:57 2018-3-26
 * @description
 */
@RestController

public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/showUser/{draw}/{start}/{length}")
    public DatatablesView<User> showUser(@PathVariable("draw") Integer draw, @PathVariable("start") Integer start, @PathVariable("length") Integer length){
        Integer page=start/length;
        Page<User> userPage=userService.showUser(page,length);
        DatatablesView<User> datatablesView=new DatatablesView<>();
        datatablesView.setDraw(draw);
        datatablesView.setData(userPage.getContent());
        datatablesView.setRecordsFiltered(userPage.getTotalElements());
        datatablesView.setRecordsTotal(userPage.getTotalElements());
        return datatablesView;
    }

    @RequestMapping("/user/deleteUser/{userId}")
    public int deleteUser(@PathVariable("userId") Long userId){
        return userService.deleteUser(userId);
    }

    @RequestMapping("/user/selectAll")
    public List<User> selectAll(){
        return userService.selectAll();
    }
}
