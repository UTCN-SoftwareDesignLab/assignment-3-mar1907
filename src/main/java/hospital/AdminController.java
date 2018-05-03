package hospital;

import hospital.dto.UserDTO;
import hospital.entity.validation.Notification;
import hospital.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("users",userService.getAll());
        model.addAttribute("user",new UserDTO());
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST, params = "Create=Create")
    public String create(@ModelAttribute("user") UserDTO user, @RequestParam String role, Model model){
        Notification<Boolean> notification = userService.create(user,role);
        if(notification.hasErrors()){
            model.addAttribute("result",notification.getFormattedErrors());
        } else {
            model.addAttribute("result","Creation succesful!");
        }

        model.addAttribute("users",userService.getAll());
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST, params = "Update=Update")
    public String update(@ModelAttribute("user") UserDTO user, @RequestParam String role, Model model){
        Notification<Boolean> notification = userService.update(user,role);
        if(notification.hasErrors()){
            model.addAttribute("result",notification.getFormattedErrors());
        } else {
            model.addAttribute("result","Update succesful!");
        }

        model.addAttribute("users",userService.getAll());
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST, params = "Delete=Delete")
    public String delete(@ModelAttribute("user") UserDTO user, Model model){
        Notification<Boolean> notification = userService.delete(user.getUsername());
        if(notification.hasErrors()){
            model.addAttribute("result",notification.getFormattedErrors());
        } else {
            model.addAttribute("result","Update succesful!");
        }

        model.addAttribute("users",userService.getAll());
        return "admin";
    }
}
