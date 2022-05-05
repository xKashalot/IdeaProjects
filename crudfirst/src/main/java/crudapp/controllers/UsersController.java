package crudapp.controllers;

import crudapp.models.User;
import crudapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private final UserServiceImpl userService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.users());
        return "users/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/view")
    public String showById(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "users/user";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("/")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model){
    model.addAttribute("user", userService.showUser(id));
    return "users/edit";
    }






//        @GetMapping("/{id}")
//    public String showById(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.showUser(id));
//        return "users/user";
//    } rest

//        @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id){
//        model.addAttribute("user", userService.showUser(id));
//        return "users/edit";
//    } rest


//        @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
//        userService.update(id, user);
//        return "redirect:/users";
//    } rest

//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        userService.delete(id);
//        return "redirect:/users";
//    } rest

}
