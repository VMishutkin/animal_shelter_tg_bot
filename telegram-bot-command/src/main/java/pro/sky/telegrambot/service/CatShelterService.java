package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.CatShelter;
import pro.sky.telegrambot.repositoty.PersonRepository;
import pro.sky.telegrambot.repositoty.ReportRepository;
@Service
public class CatShelterService extends ShelterService{
    public CatShelterService(CatShelter catShelter, PersonRepository contactRepository, ReportRepository reportRepository, TelegramBot telegramBot) {
        super(catShelter, contactRepository, reportRepository, telegramBot);
    }
}
