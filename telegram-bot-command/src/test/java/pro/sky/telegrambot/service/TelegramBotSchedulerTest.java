package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.Person;
import pro.sky.telegrambot.repository.PersonRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TelegramBotSchedulerTest {

    static Person person = new Person();

    @BeforeEach
    public void SetUp() {
        person.setId(1L);
        person.setUsername("Jack");
    }
    @Mock
    private PersonRepository personRepository;

    @Mock
    private TelegramBotScheduler telegramBotScheduler;



    @Test
    void runTest() throws InterruptedException {

    }


}
