package cn.bdqn.managerweb.controller;

import cn.bdqn.managerweb.entity.User;
import cn.bdqn.managerweb.service.UserService;
import cn.bdqn.managerweb.utils.ExportExcelSeedBack;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author syx
 * @date 14:17 2018-3-26
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/showUser")
    public String showUser(@RequestParam(value = "draw") Integer draw, @RequestParam(value = "start") Integer start, @RequestParam(value = "length") Integer length){
        return userService.showUser(draw,start,length);
    }

    @ResponseBody
    @RequestMapping("/deleteUser")
    public String deleteUser(Long userId){
        return userService.deleteUser(userId);
    }

    @ResponseBody
    @RequestMapping("/deleteAll")
    public String deleteAll(Long[] abc){
        boolean flag=true;
        for (Long a:abc){
            String s = userService.deleteUser(a);
            if(s=="2"){
                flag=false;
            }
        }
        if(flag){
            return "1";
        }else {
            return "2";
        }
    }

    @RequestMapping("/export")
     public void export(HttpServletResponse response){
        List<User> userList = userService.selectAll();
        if (userList != null && userList.size()>0) {//查询的数据不为空就对数据进行导出
            //导出文件的标题
            String title = "看看看用户"+".xls";
            //设置表格标题行
            String[] headers = new String[] {"序号","用户名", "密码","状态"};
            List<Object[]> dataList = new ArrayList<Object[]>();
            Object[] objs = null;
            int i=1;
            for (User user : userList) {//循环每一条数据
                objs = new Object[headers.length];
                objs[0] = i;//序号
                objs[1] = user.getName();//姓名
                objs[2] = user.getPassword();//姓名
                if(user.getStatus()==1){
                    objs[3]="正常";
                }else {
                    objs[3]="停用";
                }
                //数据添加到excel表格
                dataList.add(objs);
                i++;
            }
            //使用流将数据导出
            OutputStream out = null;
            try {
                //防止中文乱码
                String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
                response.setContentType("octets/stream");
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", headStr);
                out = response.getOutputStream();
                //ExportExcel ex = new ExportExcel(title, headers, dataList);//有标题
                ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);//没有标题
                ex.export(out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
