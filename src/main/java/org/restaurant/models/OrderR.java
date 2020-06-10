package org.restaurant.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderR {

    private String orderOwner;
    private List<Meal> mealList;

    public OrderR() {
        mealList = new ArrayList<>();
    }

    public OrderR(String orderOwner, List<Meal> mealList) {
        this.orderOwner = orderOwner;
        this.mealList = mealList;
    }

    public String getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(String orderOwner) {
        this.orderOwner = orderOwner;
    }

    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderR orderR = (OrderR) o;
        return getOrderOwner().equals(orderR.getOrderOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderOwner());
    }
}

