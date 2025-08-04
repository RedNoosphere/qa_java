package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {
    @Mock
    private Predator predator;

    @Test
    void constructorThrowsWhenPredatorIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new Cat(null)
        );
        assertEquals("Predator cannot be null", exception.getMessage());
    }

    @Test
    void getSoundReturnsMeow() {
        Cat cat = new Cat(predator);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void getFoodDelegatesToPredator() throws Exception {
        Cat cat = new Cat(predator);
        when(predator.eatMeat()).thenReturn(List.of("Мясо"));

        assertEquals(List.of("Мясо"), cat.getFood());
        verify(predator).eatMeat();
    }

    @Test
    void getFoodPropagatesException() throws Exception {
        Cat cat = new Cat(predator);
        when(predator.eatMeat()).thenThrow(new Exception("Ошибка"));

        assertThrows(Exception.class, cat::getFood);
    }
}