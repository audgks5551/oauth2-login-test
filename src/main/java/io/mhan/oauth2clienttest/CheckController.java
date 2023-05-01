package io.mhan.oauth2clienttest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckController {

    @GetMapping("/check")
    @ResponseBody
    public String check() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
