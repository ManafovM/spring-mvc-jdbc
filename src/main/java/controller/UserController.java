package controller;

import dao.UsersDao;
import model.User;
import model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UsersDao usersDao;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers(@RequestParam(value = "first_name", required = false) String firstName) {

        List<User> userList;
        if (firstName != null) {
            userList = usersDao.findAllByFirstName(firstName);
        } else {
            userList = usersDao.findAll();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("usersFromServer", userList);
        return modelAndView;
    }

    @RequestMapping(path = "/users/{user-id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("user-id") Long userId) {
        Optional<User> user = usersDao.find(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        user.ifPresent(value -> modelAndView.addObject("usersFromServer", Collections.singletonList(value)));
        return modelAndView;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String addUser(UserForm userForm) {
        User newUser = User.from(userForm);
        usersDao.save(newUser);
        return "redirect:users";
    }
}
