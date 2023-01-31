package pro.sky.telegrambot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.CatShelterPerson;
import pro.sky.telegrambot.service.CatShelterPersonService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatShelterPersonControllerTest {

    static CatShelterPerson person = new CatShelterPerson();

    @BeforeEach
    public void SetUp() {
        person.setId(1L);
        person.setUsername("Mike");
    }

    @InjectMocks
    CatShelterPersonController personController;

    @Mock
    private CatShelterPersonService personService;

    @Test
    void getAllPersons() {
        when(personService.getAllPersons()).thenReturn(List.of(person));
        assertEquals(personController.getAllPersons(), List.of(person));
    }

    @Test
    void findPersonById() {
        when(personService.getPerson(1L)).thenReturn(person);
        assertEquals(personController.findPersonById(1L), person);
    }

    @Test
    void createPerson() {
        when(personService.savePerson(person)).thenReturn(person);
        assertEquals(personController.createPerson(person), person);
    }

    @Test
    void editPerson() {
        when(personService.editPerson(person)).thenReturn(person);
        assertEquals(personController.editPerson(person), person);
    }

}
