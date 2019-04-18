package com.xf.mavenssm.controller;

import com.xf.maven_ssm.domain.Items;
import com.xf.maven_ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/showDetail")
    public String showDetail(Model model){
        Items items = itemsService.findById(2);
        model.addAttribute("item", items);
        return "itemDetail";
//        return "jsp/itemDetail";
//        return "itemDetail.jsp";
    }

}
