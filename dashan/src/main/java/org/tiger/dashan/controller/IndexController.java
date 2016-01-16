package org.tiger.dashan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController
{
    
    @RequestMapping("/compatible")
    public ModelAndView checkbrowser()
    {
        return new ModelAndView("compatible");
    }
    
    @RequestMapping("/")
    public ModelAndView index()
    {
        return new ModelAndView("index");
    }
    
    @RequestMapping("/products")
    public ModelAndView products()
    {
        return new ModelAndView("products");
    }
    
    @RequestMapping("/knowledge")
    public ModelAndView knowledge()
    {
        return new ModelAndView("knowledge");
    }
    
    @RequestMapping("/about")
    public ModelAndView about()
    {
        return new ModelAndView("about");
    }
    
}
