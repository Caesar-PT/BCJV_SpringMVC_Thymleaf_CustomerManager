package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;


import java.util.List;

@RequestMapping("/customers")
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Customer> customerList = customerService.findAll();
        modelAndView.addObject("list", customerList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/index");
        customerService.save(customer);
//        modelAndView.addObject("customer", customer);
//        modelAndView.addObject("mess", "Tao moi thanh cong " + customer.getName());
        List<Customer> customerList = customerService.findAll();
        modelAndView.addObject("list", customerList);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView editCustomer(@ModelAttribute Customer customer){
        ModelAndView modelAndView = new ModelAndView("redirect:" + "/customers/index");
        customerService.update(customer);
        return modelAndView;

    }

    @GetMapping("/{id}/delete")
    public ModelAndView formDel(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("{id}/delete")
    public ModelAndView del(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/customers/index");
        Customer customer = customerService.findById(id);
        customerService.remove(customer);
        return modelAndView;
    }

    @GetMapping("{id}/view")
    public ModelAndView view(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/view");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("/find")
    public ModelAndView showFormFind(){
        ModelAndView modelAndView = new ModelAndView("find");
        return modelAndView;
    }

    @PostMapping("/find")
    public ModelAndView findByName(@RequestParam String name){
        ModelAndView modelAndView = new ModelAndView("/find");
        List<Customer> customerList = customerService.findByName(name);
        modelAndView.addObject("list1", customerList);
        return modelAndView;
    }


}
