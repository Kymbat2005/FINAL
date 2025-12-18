package kz.narxoz.demo.controller;


import kz.narxoz.demo.model.User;
import kz.narxoz.demo.service.ServiceImpl.MyUserService;
import kz.narxoz.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserApi {

    private final MyUserService myUserService;
    private final StudentService studentService;

    @GetMapping
    public String get123(){
        return "test";
    }

    @PostMapping("/registr")
    public void registr(@RequestBody User model){
        myUserService.registr(model);
    }

    @GetMapping("/students")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

}