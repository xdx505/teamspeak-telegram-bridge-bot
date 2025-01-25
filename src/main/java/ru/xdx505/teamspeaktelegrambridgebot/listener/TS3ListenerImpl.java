package ru.xdx505.teamspeaktelegrambridgebot.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import org.springframework.stereotype.Service;
import ru.xdx505.teamspeaktelegrambridgebot.config.TS3Properties;
import ru.xdx505.teamspeaktelegrambridgebot.service.TS3UserService;

@Service
public class TS3ListenerImpl extends TS3EventAdapter {
    private final TS3UserService userService;
    private final TS3Properties ts3Properties;
    private final TS3Api api;

    public TS3ListenerImpl(TS3Api api, TS3UserService userService, TS3Properties ts3Properties) {
        this.api = api;
        this.userService = userService;
        this.ts3Properties = ts3Properties;
        api.addTS3Listeners(this);
    }

    @Override
    public void onClientJoin(ClientJoinEvent e) {
        if (!e.getClientNickname().startsWith("serveradmin")
                && !e.getClientNickname().startsWith(ts3Properties.getBotName())) {
            userService.notifyUserJoin(e.getClientId(), e.getClientNickname());
        }
    }
}
