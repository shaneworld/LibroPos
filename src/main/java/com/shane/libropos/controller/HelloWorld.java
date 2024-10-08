package com.shane.libropos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


/**
 * HelloWorld
 */
@Controller
public class HelloWorld {

  @GetMapping("/")
  public String hello(Model model) {
    model.addAttribute("name", "Shane");
    return "hello";
  }
}
