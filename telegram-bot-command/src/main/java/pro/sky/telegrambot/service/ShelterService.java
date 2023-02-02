package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import pro.sky.telegrambot.constant.ShelterType;
import pro.sky.telegrambot.entity.*;
import pro.sky.telegrambot.exception.TelegramBotExceptionAPI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pro.sky.telegrambot.constant.ChatSettings.volunteerChatId;
import static pro.sky.telegrambot.constant.Strings.*;


public class ShelterService {
    private final Shelter shelter;
    private final PersonService personService;
    private final ReportService reportService;
    private final Logger logger = LoggerFactory.getLogger(ShelterService.class);
    private final TelegramBot telegramBot;


    public ShelterService(Shelter shelter, PersonService personService, ReportService reportService, TelegramBot telegramBot) {
        this.shelter = shelter;
        this.personService = personService;
        this.reportService = reportService;
        this.telegramBot = telegramBot;
    }

    public void getContactFromChat(Message inputMessage, ShelterType shelterType) {
        long chatId = inputMessage.chat().id();
        String text = inputMessage.text() + " @" + inputMessage.from().username();
        SendMessage reply;
        if (personService.addContact(chatId, text, shelterType)) {
            reply = new SendMessage(chatId, "Контакт сохранен");
            telegramBot.execute(reply);
        } else {
            reply = new SendMessage(chatId, "Не удалось сохранить контакт");
            telegramBot.execute(reply);
        }
    }


    public void getReport(Message message, ShelterType shelterType) {
        Report report;
        if (shelterType == ShelterType.CAT_SHELTER)
            report = new CatShelterReport();
        else {
            report = new DogShelterReport();
        }
        report.setUsername(message.chat().username());
        try {
            report.setMessage(message.caption());
            report.setDateReport(LocalDate.now());
            PhotoSize photoSize = message.photo()[1];
            GetFile getFile = new GetFile(photoSize.fileId());
            GetFileResponse getFileResponse = telegramBot.execute(getFile);
            byte[] image = telegramBot.getFileContent(getFileResponse.file());
            report.setPhoto(image);
            reportService.saveReport(report);
        } catch (Exception e) {
            logger.error("Ошибка чтения или записи отчёта");
        } finally {
            String text = "Отчёт не сохранён, попытайтесь его отправить заново";
            if (!(report.getMessage() == null) && !(report.getPhoto() == null)) {
                text = "Благодарим за ваш отчёт";
            }
            if (report.getMessage() == null) {
                text = WARNING_MESSAGE + " Текст где?";
                SendMessage reply = new SendMessage(volunteerChatId, "Усыновитель @" + message.chat().username() + " не прислал текст");
                telegramBot.execute(reply);
            }
            if (report.getPhoto() == null) {
                text = WARNING_MESSAGE + " Фото где?";
                SendMessage reply = new SendMessage(volunteerChatId, "Усыновитель @" + message.chat().username() + " не прислал фото");
                telegramBot.execute(reply);
            }
            SendMessage reply = new SendMessage(message.chat().id(), text);
            telegramBot.execute(reply);
        }
    }

    public void getRequest(Message inputMessage) {
        String nickName = inputMessage.from().username();
        String requestText = inputMessage.text();
        SendMessage messageVolunteer = new SendMessage(volunteerChatId, MESSAGE_FOR_VOLUNTEER + "\n " + "@" + nickName + "\n" + requestText);
        telegramBot.execute(messageVolunteer);
        SendMessage replyMessage = new SendMessage(inputMessage.chat().id(), THANKS_FOR_REQUEST);
        telegramBot.execute(replyMessage);
    }

    public Shelter getShelter() {
        return shelter;
    }

    public String getAbout() {
        return shelter.getAbout();
    }

    public String getScheduleAndAdress() {
        return shelter.getScheduleAndAddress();
    }

    public String getSafetyPrecautions() {
        return shelter.getSafetyPrecautions();
    }

    public String getDocumentsForAdpotion() {
        return shelter.getDocumentsForAdoption();
    }

    public String getDeclineReasons() {
        return shelter.getDeclineReasons();
    }

    public String getMeetingRules() {
        return shelter.getMeetingRules();
    }

    public void updateInfo() {
        shelter.updateInfoAboutShelter();
    }

    public String getGreetings() {
        return shelter.getGreetings();
    }


    public String getTransportationRecommendations() {
        return shelter.getTransportationRecommendations();
    }

    public String getHomeImprovementsForAdultsRecommendations() {
        return shelter.getHomeImprovementsForAdultsRecommendations();
    }

    public String getHomeImprovementsForPuppiesRecommendations() {
        return shelter.getHomeImprovementsForPuppiesRecommendations();
    }

    public String getHomeImprovementsForDisabledRecommendations() {
        return shelter.getHomeImprovementsForDisabledRecommendations();
    }

    public void deleteContact(String message) {
        try {
            personService.deletePerson(Long.valueOf(message));
        } catch (RuntimeException e) {
            logger.error("Ошибка. Удаление не возможно, запись не найдена");
        }
    }


    public void appointGuardian(String id) {
        personService.appointGuardian(id);
    }


    public void notificationExtendProbation(Long chatID, int days) {
        SendMessage message = new SendMessage(chatID, "Ваш испытательный срок увеличен на " + days + " дней");
        telegramBot.execute(message);
    }


    public String printContactsList() {
        return personService.getAllPersons().stream().map(Objects::toString).collect(Collectors.joining("\n"));
        //       return personRepository.findAll().stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }


    public void extendProbation(String message) {
        try {
            String idString = message.substring(0, message.indexOf(" "));
            Long id = Long.valueOf(idString);
            String daysAdd = message.substring(message.indexOf(" ") + 1);
            int days = Integer.parseInt(daysAdd);
            Long chatID = personService.extendProbation(id, days);
            notificationExtendProbation(chatID, days);
        } catch (TelegramBotExceptionAPI e) {
            logger.error("Ошибка. Изменение не сохранено");
        }
    }


    public void failProbation(String inputText, Long chatID) {
        if (personService.failProbation(inputText)) {
            SendMessage failMessage = new SendMessage(chatID, FAIL_MESSAGE);
            telegramBot.execute(failMessage);
        } else {
            SendMessage voulonteerMessage = new SendMessage(volunteerChatId, "Ошибка отмены испытательного срока");
            telegramBot.execute(voulonteerMessage);
        }
    }

    public void getReportsByDay(String inputText, long chatid) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(inputText, formatter);
        List<Report> reports = reportService.getAllReportsDaily(date);
        for (Report report : reports
        ) {
            SendPhoto photo = new SendPhoto(chatid, report.getPhoto());
            String message = report.getDateReport() + " " + report.getMessage();
            photo.caption(message);
            telegramBot.execute(photo);
        }
    }

    public void getReportsByUser(String inputText, long chatid) {
        List<Report> reports = reportService.getAllReportsByPerson(inputText);
        for (Report report : reports
        ) {
            SendPhoto photo = new SendPhoto(chatid, report.getPhoto());
            String message = report.getDateReport() + " " + report.getMessage();
            photo.caption(message);
            telegramBot.execute(photo);
        }
        //  SendMessage reply = new SendMessage(volunteerChatId, reportsString);
        //    telegramBot.execute(reply);
    }

    public void addContact(long chatid, String inputText, ShelterType shelterType) {
        personService.addContact(chatid, inputText, shelterType);
    }

    public String getApprovedCynologysts() {

        return shelter.getApprovedCynologysts();
    }

    public String getCynologystsAdvices() {

        return shelter.getCynologystsAdvices();
    }


}
