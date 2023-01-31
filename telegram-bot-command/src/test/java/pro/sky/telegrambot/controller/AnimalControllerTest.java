package pro.sky.telegrambot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.Animal;
import pro.sky.telegrambot.entity.Cat;
import pro.sky.telegrambot.entity.Dog;
import pro.sky.telegrambot.service.AnimalService;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnimalControllerTest {

    private static final String KIND_OF_ANIMAL = "dog";
    static Animal dog = new Dog();
    static Animal cat = new Cat();

    @BeforeEach
    public void SetUp() {
        dog.setId(1L);
        dog.setName("Duck");
        dog.setAge(2L);
        dog.setInvalid(true);
        cat.setId(2L);
        cat.setName("Jack");
        cat.setAge(2L);
        cat.setInvalid(false);
    }

    @InjectMocks
    AnimalController animalController;

    @Mock
    private AnimalService animalService;

    @Test
    void findAnimalsKind() {
        when(animalService.getAllAnimalsKids(KIND_OF_ANIMAL)).thenReturn(List.of(dog));
        assertEquals(animalController.findAnimalsKind(KIND_OF_ANIMAL), List.of(dog));
    }

    @Test
    void findAnimalById() {
        when(animalService.getAnimal(1L)).thenReturn(dog);
        assertEquals(animalController.findAnimalById(1L), dog);
    }

    @Test
    void createDog() {
        when(animalService.createAnimal(dog)).thenReturn(dog);
        assertEquals(animalController.createDog((Dog) dog), dog);
    }

    @Test
    void createCat() {
        when(animalService.createAnimal(cat)).thenReturn(cat);
        assertEquals(animalController.createCat((Cat) cat), cat);
    }

    @Test
    void editAnimal() {
        when(animalService.editAnimal(dog)).thenReturn(dog);
        assertEquals(animalController.editAnimal(dog), dog);
    }

}
