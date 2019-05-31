package com.vng.uiwebapp.controller;

import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.uiwebapp.grpc.GrpcClient;
import com.vng.uiwebapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UIController {

    private static final int TIME_OUT = 7 * 24 * 60 * 60;

    @RequestMapping(value = {"/login","/"} )
    public String login(@CookieValue(value = "tmc", defaultValue = "BigMom") String token) {
        if(GrpcClient.isValidToken(token)){
            return "redirect:/chat";
        }
        return "login";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {

        session.invalidate();
        Cookie cookie = new Cookie("tmc", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return "redirect:/";
    }

    @RequestMapping("/chat")
    public String chat(Model model, @CookieValue(value = "tmc" , defaultValue = "Hello World") String token,
                       HttpSession session) {

        WebClientServiceOuterClass.WebsocketInfo websocketInfo = GrpcClient.getWebsocketInfo(token);
        if(websocketInfo.getEndpoint().equals("ERROR")) {
            return "redirect:/login";
        }
        model.addAttribute("endpoint", websocketInfo.getEndpoint());
        model.addAttribute("topic", websocketInfo.getTopic());
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("chatCode", websocketInfo.getChatCode());
        return "chat";
    }

    @RequestMapping("/afterLogin")
    public String redirect(User user, HttpSession session, HttpServletResponse servletResponse) {

        WebClientServiceOuterClass.Response response = GrpcClient.login(user.getUsername(), user.getPassword());
        if(response.getToken().equals("ERROR")){
         return "redirect:/login";
        }

        //save token to cookie
        Cookie cookie = new Cookie("tmc", response.getToken());
        cookie.setMaxAge(TIME_OUT);
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);
        servletResponse.addCookie(cookie);

        session.setAttribute("token", response.getToken());
        session.setAttribute("username", response.getUsername());
        return "redirect:/chat";

    }

}