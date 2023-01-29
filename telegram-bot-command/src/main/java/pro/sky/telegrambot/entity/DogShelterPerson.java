package pro.sky.telegrambot.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class DogShelterPerson extends Person{
    public DogShelterPerson(String userName, String formattedPhoneString, String contactName, long chatId) {
        super(userName, formattedPhoneString, contactName, chatId);
    }

    public DogShelterPerson() {

    }
}
