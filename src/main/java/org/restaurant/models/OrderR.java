package org.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class OrderR {

    private String orderOwner;

    @EqualsAndHashCode.Exclude
    private List<Meal> mealList;

    public OrderR() {
        mealList = new ArrayList<>();
    }
}

