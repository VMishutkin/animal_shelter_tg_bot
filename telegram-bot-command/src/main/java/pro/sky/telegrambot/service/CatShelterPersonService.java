package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.CatShelterPerson;
import pro.sky.telegrambot.entity.Person;
import pro.sky.telegrambot.repository.CatShelterPersonRepository;
import pro.sky.telegrambot.repository.PersonRepository;
@Service
public class CatShelterPersonService extends PersonService{


    protected CatShelterPersonService(CatShelterPersonRepository personRepository) {
        super(personRepository);
    }

}
