package ru.xdx505.teamspeaktelegrambridgebot.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.xdx505.teamspeaktelegrambridgebot.config.TS3Properties;
import ru.xdx505.teamspeaktelegrambridgebot.service.TS3UserServiceImpl;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TS3ListenerImplTest {
    @Mock
    private ClientJoinEvent clientJoinEvent;

    @Mock
    private TS3UserServiceImpl ts3UserService;

    @Mock
    private TS3Properties ts3Properties;

    @Mock
    private TS3Api api;

    @InjectMocks
    private TS3ListenerImpl ts3Listener;

    @Test
    void onClientJoin_shouldNotIgnoreAnyUserName() {
        when(ts3Properties.getBotName()).thenReturn("2kBOT");
        when(clientJoinEvent.getClientNickname()).thenReturn("xdx505");
        ts3Listener.onClientJoin(clientJoinEvent);
    }

    @Test
    void onClientJoin_shouldIgnoreAnyServerAdminNickname() {
        when(ts3Properties.getBotName()).thenReturn("2kBOT");
        when(clientJoinEvent.getClientNickname()).thenReturn("serveradmin1");
        ts3Listener.onClientJoin(clientJoinEvent);
    }

    @Test
    void onClientJoin_shouldIgnoreBotName() {
        when(ts3Properties.getBotName()).thenReturn("2kBOT");
        when(clientJoinEvent.getClientNickname()).thenReturn("2kBOT");
        ts3Listener.onClientJoin(clientJoinEvent);
    }
}