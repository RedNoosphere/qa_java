package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FelineTest {
    private final Feline feline = new Feline();

    // ========== Тесты для eatMeat() ==========
    @Test
    void eatMeatReturnsPredatorFood() throws Exception {
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expected, feline.eatMeat());
    }

    @Test
    void eatMeatThrowsForInvalidAnimalType() {
        Exception exception = assertThrows(Exception.class,
                () -> new Feline().getFood("Неизвестный тип"));
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник",
                exception.getMessage());
    }

    // ========== Тесты для getKittens() ==========
    @Test
    void getKittensWithoutParamReturnsOne() {
        assertEquals(1, feline.getKittens());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, Integer.MAX_VALUE})
    void getKittensWithParam(int kittensCount) {
        assertEquals(kittensCount, feline.getKittens(kittensCount));
    }

    // ========== Проверка реализации интерфейса ==========
    @Test
    void shouldImplementPredatorInterface() {
        assertTrue(feline instanceof Predator);
    }
}