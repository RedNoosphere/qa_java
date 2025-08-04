package com.example;

import java.util.List;

public class Cat {
    private final Predator predator;

    public Cat(Predator predator) {
        if (predator == null) {
            throw new NullPointerException("Predator cannot be null");
        }
        this.predator = predator;
    }

    public String getSound() {
        return "Мяу";
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }
}