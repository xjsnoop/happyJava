package cn.xujian.controller;

import cn.xujian.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
