package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.sky.telegrambot.constant.ShelterType;
import pro.sky.telegrambot.entity.CatShelterPerson;
import pro.sky.telegrambot.entity.DogShelterPerson;
import pro.sky.telegrambot.entity.Person;
import pro.sky.telegrambot.exception.TelegramBotExceptionAPI;
import pro.sky.telegrambot.repository.PersonRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Сервис для работы с сущностью Person
 */

public  class PersonService {

    private final PersonRepository personRepository;
    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }



    /**
     * Возвращает список Person
     *
     * @return
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Возвращает Person по id
     *
     * @param id
     * @return
     */
    public Person getPerson(Long id) {

        return (Person) personRepository.findById(Long.valueOf(id)).orElseThrow();
    }

    /**
     * Создаёт новую запись Person в БД
     *
     * @param person
     * @return
     */
    public Person savePerson(Person person) {
        return (Person) personRepository.save(person);
    }

    /**
     * Редактирует запись Person в БД
     *
     * @param person
     * @return
     */
    public Person editPerson(Person person) {
       return (Person) personRepository.save(person);
    }

    /**
     * Удаляет запись из БД по id
     *
     * @param id
     */
    public void deletePerson(Long id) {
        try {
            personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        logger.error("There is not animal with id = " + id);
        personRepository.deleteById(id);
    }


    public boolean failProbation(String id) {
        Person failedPerson;
        try {
            failedPerson = (Person) personRepository.findById(Long.valueOf(id)).orElseThrow();
            failedPerson.setAdoptive(false);
            failedPerson.setEndProbationDate(null);
            failedPerson.setStartProbationDate(null);
            personRepository.save(failedPerson);
            return true;
        }catch (Exception e){
            logger.error("не нашелся контакт");
        }
        return false;
    }

    public boolean addContact(long chatId, String inputText, ShelterType shelterType) {
        String parsedPhoneString = "";
        String contactNameAndUserName = "";
        Person newContact;
        try {
            Pattern phonePattern = Pattern.compile("^((8|\\+7)[\\-\\s]?)?\\(?\\d{3}\\)?[\\d\\-\\s]{7,10}");
            Pattern letterPattern = Pattern.compile("[^0-9\\+\\(\\)\\s\\-\\_]");
            Matcher letterMatcher = letterPattern.matcher(inputText);
            Matcher phoneMatcher = phonePattern.matcher(inputText);
            if (phoneMatcher.find()) {
                parsedPhoneString = phoneMatcher.group();
            }
            if (letterMatcher.find()) {
                contactNameAndUserName = inputText.substring(letterMatcher.start(0));
            }
            String contactName = contactNameAndUserName.substring(0, contactNameAndUserName.indexOf("@"));
            String userName = contactNameAndUserName.substring(contactNameAndUserName.indexOf("@"));

            String formattedPhoneString = parsedPhoneString.replaceAll("[\\s\\-\\(\\)]", "");

            if (formattedPhoneString.charAt(0) == '+' && formattedPhoneString.charAt(1) == '7') {
                formattedPhoneString = "8" + formattedPhoneString.substring(2);
            } else if (formattedPhoneString.charAt(0) == '9') {
                formattedPhoneString = "8" + formattedPhoneString;
            } else if (formattedPhoneString.charAt(0) == '7') {
                formattedPhoneString = "7" + formattedPhoneString.substring(1);
            }

            newContact = (shelterType == ShelterType.CAT_SHELTER) ?
                    new CatShelterPerson(userName, formattedPhoneString, contactName, chatId)
                    : new DogShelterPerson(userName, formattedPhoneString, contactName, chatId)
            ;
        } catch (TelegramBotExceptionAPI e) {
            logger.error("Ошибка. Контакт не удалось сохранить");
            return false;
        }
        savePerson(newContact);
        return true;
    }


    public void appointGuardian(String id) {
        try {
            Person guardian = getPerson(Long.valueOf(id));
            guardian.setAdoptive(true);
            guardian.setStartProbationDate(LocalDate.now());
            LocalDate endDate = LocalDate.now().plusDays(30);
            guardian.setEndProbationDate(endDate);
            savePerson(guardian);
        } catch (TelegramBotExceptionAPI e) {
            logger.error("Ошибка. Изменение сущности не возможно");
        }
    }


    public Long extendProbation(Long id, int days) {
        Person guardian = getPerson(id);
        LocalDate endDate = guardian.getEndProbationDate();
        guardian.setEndProbationDate(endDate.plusDays(days));
        savePerson(guardian);
        return guardian.getChatId();
    }

}
