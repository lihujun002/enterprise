package org.tiger.dashan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController
{
    
    @RequestMapping("/compatible")
    public String checkbrowser()
    {
        return "compatible";
    }
    
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
    
    @RequestMapping("/products")
    public String products()
    {
        return "products";
    }
    
    @RequestMapping("/knowledge")
    public String knowledge()
    {
        return "knowledge";
    }
    
    @RequestMapping("/about")
    public String about()
    {
        return "about";
    }
    
}
