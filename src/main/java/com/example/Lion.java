package com.example;

import java.util.List;

public class Lion {
    private final Predator predator;
    private final String sex;

    public Lion(Predator predator, String sex) {
        if (predator == null) {
            throw new NullPointerException("Predator cannot be null");
        }
        if (sex == null) {
            throw new NullPointerException("Sex cannot be null");
        }
        if (!"Самец".equals(sex) && !"Самка".equals(sex)) {
            throw new IllegalArgumentException("Используйте допустимые значения пола животного - Самец или Самка");
        }
        this.predator = predator;
        this.sex = sex;
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }

    public int getKittens() {
        return 1;
    }

    public boolean hasMane() {
        return "Самец".equals(sex);
    }
}