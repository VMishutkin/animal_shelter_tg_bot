package pro.sky.telegrambot.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.telegrambot.entity.CatShelterPerson;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Qualifier("catShelter")
public interface CatShelterPersonRepository extends PersonRepository<CatShelterPerson> {

    /**
     * метод поиска статуса человека. не используется
     * @deprecated
     * @param userName
     * @return
     */
    @Query(value = "SELECT status FROM * WHERE username = :userName", nativeQuery = true)
    Boolean getPersonStatusFromDataBase(@Param("userName") String userName);

    /**
     * Метод не используется
     *
     * @param status
     * @return
     * @deprecated
     */
    @Query(value = "SELECT * FROM person WHERE status = :status", nativeQuery = true)
    List<pro.sky.telegrambot.entity.Person> getPersonFromDataBase(@Param("status") Boolean status);

    /**
     * Метод возвращает лист пользователей из таблицы person.
     * В качестве параметра передается дата. Возвращаемые сущности Person.
     *
     * @param endDate
     * @return List<Person>
     * @deprecated
     */
    @Query(value = "SELECT * FROM person WHERE end_date = :endDate", nativeQuery = true)
    List<pro.sky.telegrambot.entity.Person> getUsernameEndDate(@Param("endDate") LocalDate endDate);

    /**
     * Запрос людей, которые сегодня прошли испытательный срок для поздравления
     *
     * @return Список людей у которых сегодня прошел испытательный срок
     */
    @Query(value = "select * from person where end_date = current_date", nativeQuery = true)
    List<pro.sky.telegrambot.entity.Person> getUsernameCompleted();
}
