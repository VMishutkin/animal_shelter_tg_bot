package pro.sky.telegrambot.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Animal;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для хранения сущности животные
 */
@Repository
@Transactional
public interface AnimalRepository extends JpaRepository<Animal, Long> {


    //List<Animal> findByKindOfAnimal(String kindOfAnimal);

    @Query(value = "SELECT * FROM animal WHERE animal_type = :type", nativeQuery = true)
    List<Animal> findByType(@Param("type")String type);

}
