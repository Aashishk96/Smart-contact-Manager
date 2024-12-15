package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        
        // sending data to view
        model.addAttribute("name", "PreDrag System LLP");
        model.addAttribute("Udemy", "Udemy Instructor too");
        model.addAttribute("GithubRepo", "https://amishagupta.dorik.io/");
        return "home";
    }

    // about 
    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", false);
        System.out.println("About page loading");
        return "about";
    }

    // services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page loading");
        return "services";
    }

    // contact
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }
    
    // this is login controller -view
    //login
    @GetMapping("/login")
    public String login() {
        // return new String("login");
        return "login";
    }
    
    //this is registration controller -view
    // register
    @GetMapping("/register")
    public String register(Model model){
        UserForm userForm = new UserForm();
        // we can send default data too
        // userForm.setName("Aashish"); // default name
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // processing register
    @RequestMapping(value = "/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult ,HttpSession session){
        System.out.println("processing registration");
        //fetch form data
        System.out.println(userForm);

        // validate form data
        if(rBindingResult.hasErrors()){
            return "register";
        }

        // save to database

        // userservice

        // data sent  userForm -> User

        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://isobarscience.com/wp-content/uploads/2020/09/default-profile-picture1.jpg")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://isobarscience.com/wp-content/uploads/2020/09/default-profile-picture1.jpg");

        User savedUser = userService.saveUser(user);

        System.out.println("user saved");

        // message = "registration successful"

        // add a message

        Message message =  Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message",message);

        // redirect to login page
        return "redirect:/register";
    }

}
