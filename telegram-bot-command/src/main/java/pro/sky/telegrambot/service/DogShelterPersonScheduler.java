package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repository.DogShelterPersonRepository;
import pro.sky.telegrambot.repository.PersonRepository;
@Service
public class DogShelterPersonScheduler extends TelegramBotScheduler{
    public DogShelterPersonScheduler(TelegramBot telegramBot, DogShelterPersonRepository personRepository) {
        super(telegramBot, personRepository);
    }
}
