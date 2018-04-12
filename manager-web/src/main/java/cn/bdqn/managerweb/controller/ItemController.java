package cn.bdqn.managerweb.controller;

import cn.bdqn.managerweb.entity.Item;
import cn.bdqn.managerweb.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author syx
 * @date 10:48 2018-3-29
 * @description
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @RequestMapping("/showItem")
    @ResponseBody
    public List<Item> showItem(HttpServletRequest request) throws Exception{
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("itemCookie")){
                    String cookieValue = cookie.getValue();
                    System.out.println(URLDecoder.decode(cookieValue,"utf-8"));
                    String decode = URLDecoder.decode(cookieValue, "utf-8");
                    List<Item> userList = JSONObject.parseArray(decode, Item.class);
                    return userList;
                }
            }
            System.out.println("3333333333333");
        }
            List<Item> itemList=new ArrayList<>();
            return itemList;
    }



    @RequestMapping("/addItem")
    @ResponseBody
    public String addItem(Item item, HttpServletRequest request, HttpServletResponse response) throws Exception{
        Cookie[] cookies = request.getCookies();
        List<Item> itemList =new ArrayList<>();
        if (cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("itemCookie")){
                    String cookieValue = cookie.getValue();
                    String decode = URLDecoder.decode(cookieValue, "utf-8");
                    itemList = JSONObject.parseArray(decode, Item.class);
                   if(itemList.size()==0){
                       itemList.add(item);
                   }else {
                       int count=0;
                       for(Item item1:itemList){
                           if(item1.getName().equals((item.getName()))){
                               item1.setNum(item1.getNum()+item.getNum());
                               count++;
                           }
                       }
                       if(count==0){
                           itemList.add(item);
                       }
                   }
                    break;
                }
            }
            String s = JSON.toJSONString(itemList);
            String s1= URLEncoder.encode(s,"utf-8");
            Cookie cookie = new Cookie("itemCookie", s1);
            cookie.setMaxAge(60 * 60 * 24 * 15);
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println("111111111111");
        }else {
            itemList.add(item);
            String s = JSON.toJSONString(itemList);
            String s1= URLEncoder.encode(s,"utf-8");
            Cookie cookie = new Cookie("itemCookie", s1);
            cookie.setMaxAge(60 * 60 * 24 * 15);//以秒为单位，这个设置的失效时间为15天
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println("22222222222");
        }
        return "1";
    }
}
