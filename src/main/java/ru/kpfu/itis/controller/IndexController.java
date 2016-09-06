package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.model.SimpleAuthUser;
import ru.kpfu.itis.model.VKAuthUser;
import ru.kpfu.itis.model.enums.AuthorityType;
import ru.kpfu.itis.repository.SimpleAuthUserRepository;
import ru.kpfu.itis.service.AuthenticationService;

@Controller
public class IndexController {

    @Autowired
    SimpleAuthUserRepository repository;

    @Autowired
    AuthenticationService service;

    @RequestMapping(value = "/")
    public String getIndexPage() {
        return "index";
    }


    @RequestMapping(value = "/test")
    public String test(){
        SimpleAuthUser user = repository.findOneByUsernameAndType("user", AuthorityType.SIMPLE);
        service.login(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }

    @RequestMapping(value = "/users")
    public String getUsersPage(Model model){

        System.out.println(((SimpleAuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return "users";
    }
}
