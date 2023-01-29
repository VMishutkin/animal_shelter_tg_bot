package pro.sky.telegrambot.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Report;

import java.time.LocalDate;
import java.util.List;

/**
 *  репозиторий для работы с отчетами
*/
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    /*   // SELECT * FROM report WHERE username = :username and shelter = :shelter  @Param("userName")String username

        @Query(value = "SELECT * FROM report WHERE username = 'Vladfame0_0'", nativeQuery = true)
        List<Report> findReportsByUser();*/
    //List<Report> findReportByUsername(String username);
    @Query(value = "SELECT * FROM report WHERE username = 'Vladfame0_0'", nativeQuery = true)
    List<Report> getReportsByUser();

    List<Report> findByDateReport(LocalDate localDate);

}
