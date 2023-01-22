package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TelegramBotUpdatesListenerTest {

    @Mock
    private UpdatesListener updatesListener;

    @Mock
    private MenuService menuService;

    @InjectMocks
    private TelegramBotUpdatesListener telegramBotUpdatesListener;


    @Test
    void processTest() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class.getResource("update.json").toURI()));
        Update update = getUpdate(json);
        verify(updatesListener).process(List.of(update));
        Assertions.assertSame(telegramBotUpdatesListener.process(List.of(update)), -1);
    }


    private Update getUpdate(String json) {
        return BotUtils.fromJson(json, Update.class);
    }

}
