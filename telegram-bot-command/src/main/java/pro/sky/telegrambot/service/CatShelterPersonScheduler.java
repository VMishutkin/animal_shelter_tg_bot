package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repository.CatShelterPersonRepository;
import pro.sky.telegrambot.repository.PersonRepository;
@Service
public class CatShelterPersonScheduler extends TelegramBotScheduler{
    public CatShelterPersonScheduler(TelegramBot telegramBot, CatShelterPersonRepository personRepository) {
        super(telegramBot, personRepository);
    }
}
