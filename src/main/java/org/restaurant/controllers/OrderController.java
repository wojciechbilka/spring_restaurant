package org.restaurant.controllers;

import org.restaurant.models.OrderR;
import org.restaurant.models.services.Restaurant;
import org.restaurant.models.services.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.restaurant.models.UserRequest;

@Controller
public class OrderController {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private Restaurant restaurant;
    private OrderR ourOrderR;

    @GetMapping("/order")
    public String orderGet(Model model){
        model.addAttribute("welcome", "Please place order and wait for preparing it");
        model.addAttribute("menu", "Our Menu: " + restaurant.getMenu().toString());
        orderDao.addOrder(new OrderR());
        ourOrderR = orderDao.getAllOrders().get(0);
        model.addAttribute("userRequest", new UserRequest());
        return "order";
    }

    @RequestMapping(params = "addOrder", method = RequestMethod.POST)
    public String orderAdd(@ModelAttribute("userRequest") UserRequest userRequest, Model model){
        model.addAttribute("welcome", "Please place order and wait for preparing it");
        model.addAttribute("menu", "Our Menu: " + restaurant.getMenu().toString());
        if (ourOrderR.getOrderOwner()==null && !userRequest.getName().equals("")){
            ourOrderR.setOrderOwner(userRequest.getName());
        }
        if(ourOrderR.getOrderOwner()==null && userRequest.getName().equals("")) {
            return "order";
        }
        if (ourOrderR.getOrderOwner()!=null){
            model.addAttribute("userName", "OrderR placing by: " + ourOrderR.getOrderOwner());
        }
        if (restaurant.checkIfMealExist(userRequest.getMealName())){
            orderDao.addMealToOrder(ourOrderR, restaurant.getMeal(userRequest.getMealName()));
        }
        if (ourOrderR.getMealList().size()>0){
            model.addAttribute("currentOrder", "Your Current OrderR: " + ourOrderR.getMealList().toString());
        }
        return "order";
    }

    @RequestMapping(params = "removeOrder", method = RequestMethod.POST)
    public String orderRemove(@ModelAttribute("userRequest") UserRequest userRequest, Model model){
        model.addAttribute("welcome", "Please place order and wait for preparing it");
        model.addAttribute("menu", "Our Menu: " + restaurant.getMenu().toString());
        if (ourOrderR.getOrderOwner()==null && !userRequest.getName().equals("")){
            ourOrderR.setOrderOwner(userRequest.getName());
        }
        if(ourOrderR.getOrderOwner()==null && userRequest.getName().equals("")) {
            return "order";
        }
        if (ourOrderR.getOrderOwner()!=null){
            model.addAttribute("userName", "OrderR placing by: " + ourOrderR.getOrderOwner());
        }
        if (orderDao.checkIfMealAppearInOurOrder(ourOrderR, restaurant.getMeal(userRequest.getMealName()))){
            orderDao.removeMealFromOrder(ourOrderR, restaurant.getMeal(userRequest.getMealName()));
        }

        if (ourOrderR.getMealList().size()>0){
            model.addAttribute("currentOrder", "Your Current OrderR: " + ourOrderR.getMealList().toString());
        }
        return "order";
    }

    @RequestMapping(params = "placeOrder", method = RequestMethod.POST)
    public String orderPlace(@ModelAttribute("userRequest") UserRequest userRequest, Model model){
        model.addAttribute("welcome", "Please place order and wait for preparing it");
        model.addAttribute("menu", "Our Menu: " + restaurant.getMenu().toString());
        if (ourOrderR.getMealList().size()>0){
            model.addAttribute("currentOrder", "Your Current OrderR: " +
                    ourOrderR.getMealList().toString());
            model.addAttribute("message", "OrderR Total: $" +
                    restaurant.calculateOrderPrice(ourOrderR));
            model.addAttribute("allOrders", printAllOrders());
        } else {
            model.addAttribute("message", "OrderR has been cancelled..");
        }
        //orderDao.removeOrder(ourOrderR);
        orderDao.addOrder(new OrderR());
        ourOrderR = orderDao.getAllOrders().get(orderDao.getAllOrders().size()-1);
        return "order";
    }

    private String printAllOrders() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(OrderR o : orderDao.getAllOrders()) {
            sb.append("Order no ").append(i).append(" ordered by ").append(o.getOrderOwner()).append(": \n");
            for(int j = 0; j < o.getMealList().size() - 1; j++) {
                sb.append(o.getMealList().get(j)).append(", ");
            }
            sb.append(o.getMealList().get(o.getMealList().size() - 1)).append(".").append("\n");
            i++;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
