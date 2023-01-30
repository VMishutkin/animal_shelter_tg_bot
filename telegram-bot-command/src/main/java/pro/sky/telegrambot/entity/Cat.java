package pro.sky.telegrambot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@DiscriminatorValue("cat")
public class Cat extends Animal{
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private CatShelterPerson person;
    public Person getPerson() {
        return person;
    }
    public void setPerson(CatShelterPerson person) {
        this.person = person;
    }

}
