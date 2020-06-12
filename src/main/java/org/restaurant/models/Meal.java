package org.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meal {

    private String name;
    private int price;

    @Override
    public String toString() {
        return getName() + "($" + getPrice() + ")";
    }
}