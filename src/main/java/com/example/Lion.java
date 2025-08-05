package com.example;

import java.util.List;

public class Lion {
    private final Feline feline;  // Зависимость на конкретный класс Feline
    private final String sex;

    public Lion(Feline feline, String sex) {
        if (feline == null) {
            throw new NullPointerException("Feline cannot be null");
        }
        if (sex == null) {
            throw new NullPointerException("Sex cannot be null");
        }
        if (!"Самец".equals(sex) && !"Самка".equals(sex)) {
            throw new IllegalArgumentException("Используйте допустимые значения пола животного - Самец или самка");
        }
        this.feline = feline;
        this.sex = sex;
    }

    public List<String> getFood() throws Exception {
        return feline.eatMeat();  // Прямой вызов метода Feline
    }

    public int getKittens() {
        return feline.getKittens();  // Делегируем Feline (если у него есть такой метод)
    }

    public boolean hasMane() {
        return "Самец".equals(sex);
    }
}