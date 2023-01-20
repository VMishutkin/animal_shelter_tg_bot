package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.CatShelter;
import pro.sky.telegrambot.repository.CatShelterPersonRepository;
import pro.sky.telegrambot.repository.ReportRepository;

@Service
public class CatShelterService extends ShelterService{
    public CatShelterService(CatShelter catShelter, CatShelterPersonRepository contactRepository, ReportRepository reportRepository, TelegramBot telegramBot) {
        super(catShelter, contactRepository, personService, reportRepository, telegramBot);
    }
}
