package pro.sky.telegrambot.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("cat_shelter")
public class CatShelterReport extends Report{
}
