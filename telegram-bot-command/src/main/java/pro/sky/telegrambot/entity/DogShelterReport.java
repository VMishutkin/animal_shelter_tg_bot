package pro.sky.telegrambot.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("dog_shelter")
public class DogShelterReport extends Report{
}
