package org.westos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.westos.utils.CaptchaUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CaptchaPictureController extends HttpServlet {

    public static String[] random;

    @RequestMapping("/captcha.png")
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        random = CaptchaUtil.random();
        resp.setContentType("image/png");
        CaptchaUtil.outputImage(random[0],resp.getOutputStream());
    }


    @RequestMapping("/result")
    @ResponseBody
    public Map<String,Object> getResult(String captcha){
        System.out.println(captcha);
        Map<String, Object> map = new HashMap<>();
        boolean b = captcha.equals(random[1]);
        System.out.println(b);
        map.put("result",b);
        return map;
    }
}
