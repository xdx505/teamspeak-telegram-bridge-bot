package ru.xdx505.teamspeaktelegrambridgebot.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import org.springframework.stereotype.Service;
import ru.xdx505.teamspeaktelegrambridgebot.service.TS3UserService;

@Service
public class TS3ListenerImpl extends TS3EventAdapter {
    private final TS3UserService userService;

    public TS3ListenerImpl(TS3Api api, TS3UserService userService) {
        this.userService = userService;
        api.addTS3Listeners(this);
    }

    @Override
    public void onClientJoin(ClientJoinEvent e) {
        if (!e.getClientNickname().startsWith("serveradmin")) {
            userService.notifyUserJoin(e.getClientId(), e.getClientNickname());
        }
    }
}
