package com.example;

import org.junit.jupiter.api.BeforeEach;
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
    private Feline feline;
    private Cat cat;

    @BeforeEach
    void setUp() {
        cat = new Cat(feline);
    }

    @Test
    void getSoundReturnsMeowTest() {
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void getFoodDelegatesToFelineTest() throws Exception {
        when(feline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        assertEquals(List.of("Животные", "Птицы", "Рыба"), cat.getFood());
        verify(feline).eatMeat();
    }

    @Test
    void getFoodPropagatesExceptionTest() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка"));
        assertThrows(Exception.class, cat::getFood);
    }
}