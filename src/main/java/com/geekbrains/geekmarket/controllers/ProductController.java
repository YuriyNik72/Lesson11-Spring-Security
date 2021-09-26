package com.geekbrains.geekmarket.controllers;


import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;
    // Shows all products
    @RequestMapping(value = "/", method = GET)
    public String showAll(
            Model model,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "0") int num,
            @RequestParam(name = "filter", required = false) String filter
    ){
        Page<Product> page;
        if(filter != null){
            page = service.getFiltered(filter, size, num);
        } else {
            page = service.findAll(size, num);
        }
        model.addAttribute("filter", filter);
        model.addAttribute("page", page);
 //       model.addAttribute("products", page.getContent());
        return "index";
    }

    // Shows form for a new product addition
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/newProduct")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "newProduct";
    }

    // Adds a new product using object from form
    // Also it works for update
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/addProduct", method = POST)
    public String addProduct(@ModelAttribute Product product){
        service.saveOrUpdate(product);
        return "redirect:/";
    }

    // Shows edit form for product with given id
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/editProduct", method = GET)
    public String editProduct(Model model, @RequestParam("id") long id){
        Product p = service.findById(id);
        model.addAttribute("product", p);
        return "editProduct";
    }

    // Deletes product with given id
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/deleteProduct", method = GET)
    public String deleteProduct(Model model, @RequestParam("id") long id){
        service.deleteById(id);
        return "redirect:/";
    }

    // Finds product with given id (as url part) and return "result" or "not found" view
    @Secured({"ROLE_USER"})
    @RequestMapping(path = "/product/{id}", method = GET)
    public String showProductByUrlId(Model model, @PathVariable(value = "id") long id){
        return findProduct(model, id);
    }

    // Finds product with given id (as get parameter) and return "result" or "not found" view
    // Calling by form
    @Secured({"ROLE_USER"})
    @RequestMapping(path = "/findId", method = GET)
    public String showProductByFormId(Model model, @RequestParam long id){
        return findProduct(model, id);
    }
    private String findProduct(Model model, long id){
        Product p = service.findById(id);
        if(p != null){
            model.addAttribute("product", p);
            return "singleProduct";
        }
        model.addAttribute("id", id);
        return "notFound";
    }
}
