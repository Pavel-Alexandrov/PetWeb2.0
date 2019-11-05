package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    public UserService userService;


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("userList", userService.getAllUsers());

        return "users";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test() {
        return "redirect:/users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserGet(Model model) {
        model.addAttribute("user", new User());

        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") User user) {
        userService.addUser(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);

        return "redirect:/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateUserGet(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("user") User user) {
        userService.updateUser(user);

        return "redirect:/";
    }
}
