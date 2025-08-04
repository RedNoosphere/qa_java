package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {
    @Mock
    private Predator predator;

    // ===== Тесты конструктора =====
    @ParameterizedTest
    @ValueSource(strings = {"Самец", "Самка"})
    void constructorAcceptsValidSex(String validSex) {
        assertDoesNotThrow(() -> new Lion(predator, validSex));
    }

    @Test
    void constructorThrowsForInvalidSex() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Lion(predator, "Гермафродит")
        );
        assertEquals("Используйте допустимые значения пола животного - Самец или Самка",
                exception.getMessage());
    }

    @Test
    void constructorThrowsForNullSex() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new Lion(predator, null)
        );
        assertEquals("Sex cannot be null", exception.getMessage());
    }

    @Test
    void constructorThrowsForNullPredator() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new Lion(null, "Самец")
        );
        assertEquals("Predator cannot be null", exception.getMessage());
    }

    // ===== Остальные тесты остаются без изменений =====
    @Test
    void getFoodDelegatesToPredator() throws Exception {
        Lion lion = new Lion(predator, "Самец");
        when(predator.eatMeat()).thenReturn(List.of("Мясо"));
        assertEquals(List.of("Мясо"), lion.getFood());
    }

    @Test
    void getKittensAlwaysReturnsOne() {
        Lion lion = new Lion(predator, "Самка");
        assertEquals(1, lion.getKittens());
    }

    @Test
    void hasManeReturnsTrueForMale() {
        Lion lion = new Lion(predator, "Самец");
        assertTrue(lion.hasMane());
    }

    @Test
    void hasManeReturnsFalseForFemale() {
        Lion lion = new Lion(predator, "Самка");
        assertFalse(lion.hasMane());
    }
}