package com.vng.uiwebapp.controller;

import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.uiwebapp.grpc.GrpcClient;
import com.vng.uiwebapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UIController {

    @RequestMapping(value = {"/login","/"} )
    public String login() {
        return "login";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/chat")
    public String chat(HttpSession session){

        if(session.getAttribute("user")==null)
            return "redirect:/login";
        WebClientServiceOuterClass.WebsocketInfo websocketInfo = GrpcClient.getWebsocketInfo(session.getAttribute("user").toString());
        if(websocketInfo.getEndpoint().equals("ERROR")) {
            return "redirect:/login";
        }
        return "chat";
    }

    @RequestMapping("/afterLogin")
    public String redirect(User user, HttpSession session){

        String token = GrpcClient.login(user.getUsername(), user.getPassword());
        if(token.equals("ERROR")){
         return "redirect:/login";
        }
        session.setAttribute("user", token);
        return "redirect:/chat";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping("/afterRegister")
    public String rediect(User user, ModelMap model){



        return "/chat";
    }
}
