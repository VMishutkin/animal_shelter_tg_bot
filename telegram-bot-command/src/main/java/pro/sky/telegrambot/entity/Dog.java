package pro.sky.telegrambot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@DiscriminatorValue("dog")
public class Dog extends Animal{
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private DogShelterPerson person;
    public Person getPerson() {
        return person;
    }
    public void setPerson(DogShelterPerson person) {
        this.person = person;
    }

}
