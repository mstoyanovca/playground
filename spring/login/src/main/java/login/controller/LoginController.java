package login.controller;

import jakarta.validation.Valid;
import login.entity.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<LoginUser> login(@RequestBody @Valid LoginUser user) {
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
