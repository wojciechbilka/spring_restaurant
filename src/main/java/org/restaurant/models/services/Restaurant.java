package org.restaurant.models.services;

import org.restaurant.models.Meal;
import org.restaurant.models.MealDatabase;
import org.restaurant.models.OrderR;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Restaurant {

    private List<Meal> menu = new MealDatabase().getMeals();

    public boolean checkIfMealExist(String mealName){
        for (Meal meal : menu) {
            if (mealName.equals(meal.getName())){
                return true;
            }
        }
        return false;
    }

     public int calculateOrderPrice(OrderR orderR){
        int sum = 0;
        //todo implement
         for(Meal m : orderR.getMealList()) {
             sum += m.getPrice();
         }
        return sum;
    }

    public Meal getMeal(String mealName){
        //todo implement
        MealDatabase md = new MealDatabase();
        for(Meal m : md.getMeals()) {
            if(m.getName().equals(mealName)) {
                return m;
            }
        }
        return null;
    }

    public List<Meal> getMenu() {
        return menu;
    }
}
