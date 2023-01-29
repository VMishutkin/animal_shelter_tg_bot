package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.CatShelterPerson;
import pro.sky.telegrambot.entity.DogShelterPerson;
import pro.sky.telegrambot.entity.Person;
import pro.sky.telegrambot.repository.DogShelterPersonRepository;
import pro.sky.telegrambot.repository.PersonRepository;
@Service
public class DogShelterPersonService extends PersonService{

    protected DogShelterPersonService(DogShelterPersonRepository personRepository) {
      super(personRepository);
    }


}
