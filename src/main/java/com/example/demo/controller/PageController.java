package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class PageController {
    @RequestMapping("/viral")
    public String viral(){
        return "viral";
    }

    /*@RequestMapping("/challenge")
    public String challenge(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "challenge";
    }*/

    @RequestMapping(value = {"/challenge", "challenge/{name}"})
    public String challengePath(
            @PathVariable Optional<String> name,
            Model model)
    {
        if (name.isPresent()){
            model.addAttribute("name", name.get());
        }
        else {
            model.addAttribute("name", "KB");
        }
        return "challenge";
    }

    @RequestMapping("/generator")
    public String generate(
            @RequestParam(value = "a", defaultValue = "0") Integer a,
            @RequestParam(value = "b", defaultValue = "0") Integer b,
            Model model)
    {
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("kata", wordFactory(a,b));
        return "viral_generator";
    }

    public String wordFactory(Integer a, Integer b){
        String word = "h";
        if ((a == 0 || a == 1) && (b == 0 || b == 1)){
            return "hm";
        }

        else {
            if (a == 0 || a == 1) {
                word += "m";
            }
            else {
                for (int i = 0; i < a; i++) {
                    word += "m";
                }
            }

            String wordOnce = word;
            for (int i = 1 ; i < b ; i++){
                word = word + " " + wordOnce;
            }
            return word;
        }
    }
}
