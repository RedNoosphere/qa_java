package com.example;

import org.junit.jupiter.api.BeforeEach;
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
    private Feline feline;
    private Lion lion;

    @BeforeEach
    void setUp() {
            }

    @ParameterizedTest
    @ValueSource(strings = {"Самец", "Самка"})
    void constructorAcceptsValidSexTest(String validSex) {
        assertDoesNotThrow(() -> new Lion(feline, validSex));
    }

    @Test
    void constructorThrowsForInvalidSexTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Lion(feline, "Гермафродит"));
    }

    @Test
    void constructorThrowsForNullSexTest() {
        assertThrows(NullPointerException.class,
                () -> new Lion(feline, null));
    }

    @Test
    void constructorThrowsForNullFelineTest() {
        assertThrows(NullPointerException.class,
                () -> new Lion(null, "Самец"));
    }

    @Test
    void getFoodDelegatesToFelineTest() throws Exception {
        Lion lion = new Lion(feline, "Самец");
        when(feline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());
        verify(feline).eatMeat();
    }

    @Test
    void getKittensDelegatesToFelineTest() {
        Lion lion = new Lion(feline, "Самка");
        when(feline.getKittens()).thenReturn(1);

        assertEquals(1, lion.getKittens());
        verify(feline).getKittens();
    }

    @Test
    void hasManeReturnsTrueForMaleTest() {
        Lion lion = new Lion(feline, "Самец");
        assertTrue(lion.hasMane());
    }

    @Test
    void hasManeReturnsFalseForFemaleTest() {
        Lion lion = new Lion(feline, "Самка");
        assertFalse(lion.hasMane());
    }
}