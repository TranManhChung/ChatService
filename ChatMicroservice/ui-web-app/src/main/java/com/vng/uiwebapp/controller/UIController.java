package com.vng.uiwebapp.controller;

import com.vng.apigateway.WebClientServiceOuterClass;
import com.vng.uiwebapp.grpc.GrpcClient;
import com.vng.uiwebapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("username", session.getAttribute("username"))    ;
        model.addAttribute("chatCode", websocketInfo.getChatCode());
        return "chat";
    }

    @RequestMapping("/afterLogin")
    public String redirect(User user, HttpSession session, HttpServletResponse servletResponse) {

        WebClientServiceOuterClass.Response response = GrpcClient.login(user.getEmail(), user.getPassword());
        if(response.getToken().equals("ERROR")){
         return "redirect:/login";
        }
        servletResponse.addCookie(addSessionAndCookie(response.getUsername(),response.getToken(), session));
        return "redirect:/chat";

    }

    @RequestMapping("/login-google")
    public String loginGoogle(HttpServletRequest request, HttpSession session, HttpServletResponse servletResponse) {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return "redirect:/403";
        }
        //check request at here
        WebClientServiceOuterClass.Response response = GrpcClient.checkGoogleLogin(code);
        servletResponse.addCookie(addSessionAndCookie(response.getUsername(),response.getToken(),session));
        return "redirect:/chat";
    }

    public static Cookie addSessionAndCookie(String username ,String token, HttpSession session){
        //save token to cookie
        Cookie cookie = new Cookie("tmc", token);
        cookie.setMaxAge(TIME_OUT);
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);

        session.setAttribute("token", token);
        session.setAttribute("username", username);

        return cookie;
    }

    @RequestMapping("/register")
    public String register(){
        return "/register";
    }

    @RequestMapping("/afterRegister")
    public String register(User user, Model model){
        WebClientServiceOuterClass.Message message = GrpcClient.register(user);

        if (message.getMessage().equals("REGISTERED")) {
            return "/login";
        }

        model.addAttribute("message", "Your email is already in use!");

        return "/register";
    }

    @RequestMapping(value = "confirmregister", method = RequestMethod.GET)
    public String confirmRegister(@RequestParam("token") String token){
        System.out.println("Token: " + token);
        GrpcClient.confirm(token);

        return "/login";
    }

    @RequestMapping("/forgot")
    public String forgot(){
        return "forgot";
    }

    @RequestMapping("/afterForgotSubmit")
    public String forgot(User user, Model model){
        if (user.getEmail() == null){
            model.addAttribute("message", "Email is incorrect");

            return "/forgot";
        }

       WebClientServiceOuterClass.Message message = GrpcClient.forgot(user.getEmail());

        System.out.println(message);

        model.addAttribute("message", message);

        return "/forgot";
    }

    String token = "";

    @RequestMapping(value = "confirmchangepassword", method = RequestMethod.GET)
    public String confirmChange(@RequestParam("token") String token){
        this.token = token;

        return "/changepass";
    }

    @RequestMapping("/afterSubmitChangePass")
    public String changePass(User user, Model model){
        if (!user.getPassword().equals(user.getConfirm())) {
            model.addAttribute("message", "Your password is incorrect");

            return "/changepass";
        }

        GrpcClient.changePass(token, user.getPassword());

        return "/login";
    }
}