package cn.xujian.controller;

import cn.xujian.domain.User;
import cn.xujian.domain.VO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    //页面跳转

    @RequestMapping(value = "/quick",method = RequestMethod.GET)
    public String saves(){
        //此处return的为要跳转的示图
        return "success.jsp";
    }
    //使用ModelAndView最基本的操作
    @RequestMapping(value = "/quick2",method = RequestMethod.GET)
    public ModelAndView saves2(){
        /*
        * Model :模型用于封装数据
        * View  :视图用于展示数据
        *  */
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username","xujian");
        //设置视图名称
        modelAndView.setViewName("success.jsp");
        System.out.println("Controller save running");
       return modelAndView;
    }
    //由spring容器提供modelAndView对象
    @RequestMapping(value = "/quick3")
    public ModelAndView saves3(ModelAndView modelAndView){
        //设置模型数据
        modelAndView.addObject("username","quick3");
        //设置视图名称
        modelAndView.setViewName("success.jsp");
        return modelAndView;
    }
    //model和view分开
    @RequestMapping(value = "/quick4")
    public String saves4(Model model){
        //设置模型数据
        model.addAttribute("username","quick4");
        //此处return的为要跳转的示图
        return "success.jsp";
    }

    //回写数据

    //最基本的利用httpServletResponse对象回写
    @RequestMapping("/quick5")
    public void saves5(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.getWriter().write("hello xujian");
    }

    //将需要回写的字符串直接返回，通过@ResponseBody注解告知SpringMVC框架，方法返回的字符串不是跳转，是直接在http响应体中返回。
    @RequestMapping("/quick6")
    @ResponseBody
    public String saves6(){
        return "hello quick6";
    }

    //将对象转换为json字符串然后响应
    @RequestMapping("/quick7")
    @ResponseBody
    public String saves7() throws JsonProcessingException {
        User user = new User("李四",18);
        //使用json的转换工具将对象转换成json格式的字符串再返回，（导包jackson-annotations、jackson-databind、jackson-core）
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        return json;
    }

    //期望SpringMVC自动将User转换成json格式的字符串
    @RequestMapping("/quick8")
    @ResponseBody
    public User saves8()  {
        User user = new User("zhangshan",18);
        return user;
    }

    //SpringMVC获取请求参数

    //获得基本类型参数
    @RequestMapping("/quick9")
    @ResponseBody
    public void saves9(String username)  {
        System.out.println(username);
    }

    //获取POJO类型参数
    @RequestMapping("/quick10")
    @ResponseBody
    public void saves10(User user)  {
        System.out.println(user);
    }

    //获取数组参数
    @RequestMapping("/quick11")
    @ResponseBody
    public void saves11(String[] strs,String[] strs1)  {
        System.out.println(Arrays.asList(strs));
        System.out.println(Arrays.asList(strs1));
    }

    //获取集合类型参数
    @RequestMapping("/quick12")
    @ResponseBody
    public void saves12(VO vo)  {
        System.out.println(vo);
    }

    //获取集合类型参数(特殊情况)
    @RequestMapping("/quick13")
    @ResponseBody
    public void saves13(@RequestBody List<User> userList)  {
        System.out.println(userList);
    }

    //参数绑定注解@RequestParam
    @RequestMapping("/quick14")
    @ResponseBody
    public void saves14(@RequestParam("name") String username)  {
        System.out.println(username);
    }

    //获得Restful风格的参数
    @RequestMapping(value = "/quick15/{name}",method = RequestMethod.GET)
    @ResponseBody
    public void saves15(@PathVariable(value = "name", required = true) String username){
        System.out.println(username);
    }

    //自定义格式转换
    @RequestMapping("/quick16")
    @ResponseBody
    public void saves16(Date date){
        System.out.println(date);
    }

    //获得请求头
    @RequestMapping("/quick17")
    @ResponseBody
    //@RequestHeader注解的value对应请求头里的属性名
    public void saves17(@RequestHeader(value = "User-Agent") String user_agent){
        System.out.println(user_agent);
    }

    //单文件上传
    @RequestMapping("/quick18")
    @ResponseBody
    //此处的MultipartFile对象的名字要和表单上传文件的value值一样
    public void fileUpload(String username, MultipartFile uploadFile) throws IOException {
        System.out.println(username);
        //获取文件名称
        String originalFilename = uploadFile.getOriginalFilename();
        System.out.println(originalFilename);
        //保存文件
        uploadFile.transferTo(new File("C:\\Users\\xj\\Desktop\\"+originalFilename));
    }

    //多文件上传
    @RequestMapping("/quick19")
    @ResponseBody
    //此处的MultipartFile对象的名字要和表单上传文件的value值一样
    public void fileUploads(String username, MultipartFile[] uploadFile) throws IOException {
        System.out.println(username);
        //获取文件名称
        for (MultipartFile multipartFile : uploadFile){
            String originalFilename = multipartFile.getOriginalFilename();
            if (!originalFilename.equals("")){
                System.out.println(originalFilename);
                //保存文件
                multipartFile.transferTo(new File("C:\\Users\\xj\\Desktop\\"+originalFilename));
            }
        }
    }
}



