//package org.myApplication.extern.controllers;
//
//import org.myApplication.app.User;
//import org.myApplication.app.repositories.UserRepository;
//import org.myApplication.domain.enums.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.Collections;
//import java.util.Map;
//
//@Controller
//public class RegistrationController {
//    @Autowired
//    private UserRepository userRepository;
//    @GetMapping("/registration")
//    public String registration()
//    {
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String addUser(User user, Map<String, Object> model)
//    {
//        User userFromDb = userRepository.findByNickname(user.getNickname());
//        if (userFromDb != null)
//        {
//            model.put("message", "User exists");
//            return "registration";
//        }
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        userRepository.save(user);
//        return "redirect:/login";
//    }
//}
