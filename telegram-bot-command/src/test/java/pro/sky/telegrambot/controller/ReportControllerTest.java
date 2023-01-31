package pro.sky.telegrambot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.CatShelterReport;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.repository.ReportRepository;
import pro.sky.telegrambot.service.ReportService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    private static final String USERNAME = "Nik";
    static Report report = new CatShelterReport();
    static LocalDate localDate = LocalDate.ofEpochDay(-2022);

    @BeforeEach
    public void SetUp() {
        report.setId(1L);
        report.setUsername(USERNAME);
        report.setMessage("fdfd");
        report.setPhoto(new byte[10]);
        report.setDateReport(localDate);
    }

    @InjectMocks
    ReportController reportController;

    @Mock
    private ReportService reportService;

    @Test
    void getAllReportsPerson() {
        when(reportService.getAllReportsByPerson(USERNAME)).thenReturn(List.of(report));
        assertEquals(reportController.getAllReportsPerson(USERNAME), List.of(report));
    }

    @Test
    void getAllReportsDaily() {
        when(reportService.getAllReportsDaily(localDate)).thenReturn(List.of(report));
        assertEquals(reportController.getAllReportsDaily(localDate), List.of(report));
    }

}
