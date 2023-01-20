package pro.sky.telegrambot.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Person;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс JPA репозитория для сохранения контактов
 */
@NoRepositoryBean
public interface PersonRepository<T extends Person> extends JpaRepository<T, Long> {

    /**
     * метод поиска статуса человека. не используется
     * @deprecated
     * @param userName
     * @return
     */
     Boolean getPersonStatusFromDataBase(@Param("userName") String userName);

    /**
     * Метод не используется
     * @deprecated
     * @param status
     * @return
     */
    List<Person> getPersonFromDataBase(@Param("status") Boolean status);

    /** Метод возвращает лист пользователей из таблицы person.
     * В качестве параметра передается дата. Возвращаемые сущности Person.
     * @deprecated
     * @param endDate
     * @return List<Person>
     */
    List<Person> getUsernameEndDate(@Param("endDate") LocalDate endDate);

    /**
     * Запрос людей, которые сегодня прошли испытательный срок для поздравления
     * @return Список людей у которых сегодня прошел испытательный срок
     */
     List<Person> getUsernameCompleted();
}
