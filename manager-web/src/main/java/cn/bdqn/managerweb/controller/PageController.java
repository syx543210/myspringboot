package cn.bdqn.managerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author syx
 * @date 11:27 2018-3-26
 * @description
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/index")
    public String goToIndex(){
        return "showUser";
    }

    @RequestMapping("/itemList")
    public String goToItemList(){
        return "myCookie";
    }

    @RequestMapping("/addItem")
    public String goToAddItem(){
        return "addCookie";
    }
}
