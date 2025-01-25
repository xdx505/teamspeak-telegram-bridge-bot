package ru.xdx505.teamspeaktelegrambridgebot.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.checkerframework.framework.qual.RequiresQualifier;
import org.springframework.stereotype.Service;
import ru.xdx505.teamspeaktelegrambridgebot.config.TS3Properties;
import ru.xdx505.teamspeaktelegrambridgebot.service.TS3UserService;

@Service
@RequiredArgsConstructor
public class TS3ListenerImpl extends TS3EventAdapter {
    private final TS3UserService userService;
    private final TS3Properties ts3Properties;
    private final TS3Api ts3Api;

    @PostConstruct
    private void initListeners() {
        ts3Api.addTS3Listeners(this);
    }

    @Override
    public void onClientJoin(ClientJoinEvent e) {
        if (!e.getClientNickname().startsWith("serveradmin")
                && !e.getClientNickname().startsWith(ts3Properties.getBotName())) {
            userService.notifyUserJoin(e.getClientId(), e.getClientNickname());
        }
    }
}
