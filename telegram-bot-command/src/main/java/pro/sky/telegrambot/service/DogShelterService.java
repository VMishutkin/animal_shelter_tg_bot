package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.DogShelter;
import pro.sky.telegrambot.repository.DogShelterPersonRepository;
import pro.sky.telegrambot.repository.ReportRepository;

/**
 * Пока в работе
 */

@Service
public class DogShelterService extends ShelterService {

    public DogShelterService(DogShelter dogShelter, DogShelterPersonService personService, ReportService reportService, TelegramBot telegramBot) {

        super(dogShelter, personService, reportService, telegramBot);
    }

    public String getApprovedCynologysts() {
        DogShelter dogShelter = (DogShelter) this.getShelter();
        return dogShelter.getApprovedCunologysts();
    }

    public String getCynologystsAdvices() {
        DogShelter dogShelter = (DogShelter) this.getShelter();
        return dogShelter.getCynologystsAdvices();
    }


}
