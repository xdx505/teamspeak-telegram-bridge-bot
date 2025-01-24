package ru.xdx505.teamspeaktelegrambridgebot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.xdx505.teamspeaktelegrambridgebot.config.TS3Properties;
import ru.xdx505.teamspeaktelegrambridgebot.entity.TS3User;
import ru.xdx505.teamspeaktelegrambridgebot.repository.TS3UserRepositoryImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TS3UserServiceImplTest {
    @Mock
    private TS3Properties properties;

    @Mock
    private TS3UserRepositoryImpl repository;

    @Mock
    private TGNotifyServiceImpl notifyService;

    @InjectMocks
    private TS3UserServiceImpl service;

    @BeforeEach
    void setUp() {
        when(properties.getMessageTimeoutInMinutes()).thenReturn(10);
    }

    @Test
    void testNewUser_shouldSendMessage() {
        when(repository.getUser(anyString())).thenReturn(null);
        service.notifyUserJoin(1, "nick");
        verify(notifyService, times(1)).sendMessage(anyString());
    }

    @Test
    void testUser_ifTimeout_shouldSendMessage() throws ParseException {
        TS3User user = mock(TS3User.class);
        when(user.id()).thenReturn(1);
        when(user.nickname()).thenReturn("test");
        when(user.createDate()).thenReturn(new SimpleDateFormat("dd.MM.yyyy").parse("05.02.2022"));

        when(repository.getUser(anyString())).thenReturn(user);

        service.notifyUserJoin(1, "nick");
        verify(notifyService, times(1)).sendMessage(anyString());
    }

    @Test
    void testUser_ifNoTimeout_shouldNotSendMessage() {
        TS3User user = mock(TS3User.class);
        when(user.id()).thenReturn(1);
        when(user.nickname()).thenReturn("test");
        when(user.createDate()).thenReturn(new Date());

        when(repository.getUser(anyString())).thenReturn(user);

        service.notifyUserJoin(1, "nick");
        verify(notifyService, times(0)).sendMessage(anyString());
    }
}