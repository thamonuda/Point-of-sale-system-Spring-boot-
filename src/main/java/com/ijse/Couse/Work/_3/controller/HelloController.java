package com.ijse.Couse.Work._3.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class HelloController {
    
@GetMapping("/hello")
public String sayHello(){
    return "say hello";
}

@PostMapping("/hello")
public String postHello(){
    return "say hello post hello";
}


}

