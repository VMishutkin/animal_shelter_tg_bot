package pro.sky.telegrambot.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Animal;

import java.util.List;

/**
 * Репозиторий для хранения сущности животные
 */
@Repository
@Scope("prototype")
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByKindOfAnimal(String kindOfAnimal);

}
