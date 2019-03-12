package danielh1307.springbootsecurityexample.ctrl;

import danielh1307.springbootsecurityexample.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private CurrentUserProvider currentUserProvider;

    HelloController(CurrentUserProvider currentUserProvider) {
        this.currentUserProvider = currentUserProvider;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello " + currentUserProvider.getCurrentUser().getFirstname();
    }

    @GetMapping("/everyone")
    public String everyone() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "hello " + currentUser.getFirstname();
    }

    // curl http://localhost:8080/role --> leads to 200
    // curl --header "role: false" http://localhost:8080/role --> leads to 403
    @GetMapping("/role")
    @PreAuthorize("@auth.userHasRole()")
    public String onlyWithRole() {
        return "hello";
    }
}
