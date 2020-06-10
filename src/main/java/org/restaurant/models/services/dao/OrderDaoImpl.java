package org.restaurant.models.services.dao;

import org.restaurant.models.Meal;
import org.restaurant.models.OrderR;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class OrderDaoImpl implements OrderDao {

    private List<OrderR> orderList;

    public OrderDaoImpl() {
        orderList = new ArrayList<>();
    }

    @Override
    public List<OrderR> getAllOrders() {
        return orderList;
    }

    @Override
    public void addOrder(OrderR order) {
        orderList.add(order);
    }

    @Override
    public void removeOrder(OrderR order) {
        for (OrderR order1 : orderList) {
            if (order1.equals(order)){
                orderList.remove(order);
                return;
            }
        }
    }

    @Override
    public void addMealToOrder(OrderR order, Meal meal) {
        //todo repair
        if(orderList.isEmpty()) {
            order.getMealList().add(meal);
            orderList.add(order);
            return;
        }
        for(OrderR o : orderList) {
            if(o.equals(order)) {
                o.getMealList().add(meal);
            }
        }
    }

    @Override
    public void removeMealFromOrder(OrderR order, Meal meal) {
        if(orderList.isEmpty()) {
            return;
        }
        for(OrderR o : orderList) {
            if(o.equals(order)) {
                o.getMealList().remove(meal);
            }
        }
    }

    @Override
    public boolean checkIfMealAppearInOurOrder(OrderR order, Meal meal){
        //todo repair
        for (Meal m : order.getMealList()) {
            if (m.getName().equals(meal.getName())){
                return true;
            }
        }
        return false;
    }
}
