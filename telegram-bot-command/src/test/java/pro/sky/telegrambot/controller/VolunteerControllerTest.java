package pro.sky.telegrambot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;
import pro.sky.telegrambot.service.VolunteerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VolunteerControllerTest {

    static Volunteer volunteer = new Volunteer();

    @BeforeEach
    public void SetUp() {
        volunteer.setId(1L);
        volunteer.setUsername("Mike");
    }

    @InjectMocks
    VolunteerController volunteerController;

    @Mock
    private VolunteerService volunteerService;

    @Test
    void getAllVolunteer() {
        when(volunteerService.getAllVolunteer()).thenReturn(List.of(volunteer));
        assertEquals(volunteerController.getAllVolunteer(), List.of(volunteer));
    }

    @Test
    void findVolunteerById() {
        when(volunteerService.getVolunteer(1L)).thenReturn(volunteer);
        assertEquals(volunteerController.findVolunteerById(1L), volunteer);
    }

    @Test
    void createVolunteer() {
        when(volunteerService.createVolunteer(volunteer)).thenReturn(volunteer);
        assertEquals(volunteerController.createVolunteer(volunteer), volunteer);
    }

    @Test
    void editVolunteer() {
        when(volunteerService.editVolunteer(volunteer)).thenReturn(volunteer);
        assertEquals(volunteerController.editVolunteer(volunteer), volunteer);
    }

}
