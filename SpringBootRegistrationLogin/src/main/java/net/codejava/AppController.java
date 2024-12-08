package net.codejava;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "welcome";
    }

    @GetMapping("/home")
    public String Home(Model model) {
        model.addAttribute("Home");
        return "home";
    }

    @GetMapping("/exam")
    public String Exam(Model model) {
        model.addAttribute("Exam");
        return "Exam";
    }

    @GetMapping("/welcome")
    public String Welcome(Model model) {
        model.addAttribute("welcome");
        return "welcome";
    }
    @GetMapping("/sde")
    public String Software(Model model) {
    	model.addAttribute("sde");
    	return "Software_Dev";
    }
    @GetMapping("/ds")
    public String DataScience(Model model) {
    	model.addAttribute("ds");
    	return "datascience";
    }
    @GetMapping("/web_designer")
    public String webDesigner(Model model) {
    	model.addAttribute("web_designer");
    	return "webDesigner";
    }
    @GetMapping("/digital_marketer")
    public String digital(Model model) {
    	model.addAttribute("digital_marketer");
    	return "digital";
    }
    @GetMapping("/project_manager")
    public String ProMar(Model model) {
    	model.addAttribute("project_manager");
    	return "ProMar";
    }
    @GetMapping("/ux_ui_designer")
    public String UiUx(Model model) {
    	model.addAttribute("ux_ui_designer");
    	return "UiUx";
    }
    @GetMapping("/profile")
    public String Profile(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        // Fetch the user based on the email from Principal
        String userEmail = principal.getName();
        User user = userRepo.findByEmail(userEmail);

        if (user == null) {
            model.addAttribute("error", "User not found");
            return "error"; // Create an error page if needed
        }

        // Add user details to the model
        model.addAttribute("user", user);
        return "profile"; // Return the profile view
    }
}
