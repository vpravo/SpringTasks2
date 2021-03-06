package ru.javamentor.karimov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javamentor.karimov.model.User;
import ru.javamentor.karimov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class EditUserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public String handlerUserForm(Model model, HttpServletRequest request, HttpServletResponse response){
        long id = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserByID(id);
        System.out.println(user);
        model.addAttribute("user", user);
        return "userform";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String handlerUserUpdate(Model model, HttpServletRequest request, HttpServletResponse response){
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        User user = new User(id, name, address);
        userService.updateUser(user);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }
}
