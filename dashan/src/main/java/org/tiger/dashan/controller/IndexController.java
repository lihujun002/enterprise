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
    public ModelAndView view()
    {
        return new ModelAndView("index");
    }
    
}
