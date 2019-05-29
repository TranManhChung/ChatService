package com.vng.uiwebapp.controller;

import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.uiwebapp.grpc.GrpcClient;
import com.vng.uiwebapp.model.User;
import io.grpc.Grpc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String chat(Model model, HttpSession session){

        if(session.getAttribute("user")==null)
            return "redirect:/login";
        WebClientServiceOuterClass.WebsocketInfo websocketInfo = GrpcClient.getWebsocketInfo(session.getAttribute("user").toString());
        if(websocketInfo.getEndpoint().equals("ERROR")) {
            return "redirect:/login";
        }
        model.addAttribute("endpoint", websocketInfo.getEndpoint());
        model.addAttribute("topic", websocketInfo.getTopic());
        model.addAttribute("username", session.getAttribute("username"));
        return "chat";
    }

    @RequestMapping("/afterLogin")
    public String redirect(User user, Model model, HttpSession session){

        WebClientServiceOuterClass.Response response = GrpcClient.login(user.getUsername(), user.getPassword());
        if(response.getToken().equals("ERROR")){
         return "redirect:/login";
        }
        session.setAttribute("user", response.getToken());
        session.setAttribute("username", response.getUsername());
        return "redirect:/chat";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping("/afterRegister")
    public String rediect(User user){

        if (!user.getPassword().equals(user.getConfirm())){
            return "/register";
        }

        WebClientServiceOuterClass.Message message = GrpcClient.register(user);

        if (message.getMessage().equals("REGISTERED")){
            return "/login";
        }

        return "redirect:/403";
    }
}

@RestController
class Test{

    @PostMapping("/storeSession")
    public String storeSession(String token, HttpSession session){
        session.setAttribute("user", token);
        return "done";
    }

}