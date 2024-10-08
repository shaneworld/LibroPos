package com.shane.libropos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * LoginController
 */
@Controller
public class LoginController {
  
  @GetMapping("/login")
  public String Login() {
    return "sign";
  }
}
