package ru.xdx505.teamspeaktelegrambridgebot.service;

import org.springframework.stereotype.Service;
import ru.xdx505.teamspeaktelegrambridgebot.config.TS3Properties;
import ru.xdx505.teamspeaktelegrambridgebot.entity.TS3User;
import ru.xdx505.teamspeaktelegrambridgebot.repository.TS3UserRepository;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TS3UserServiceImpl implements TS3UserService {
    private final TS3UserRepository repository;
    private final TGNotifyService notifyService;
    private final TS3Properties properties;

    public TS3UserServiceImpl(TS3UserRepository repository, TGNotifyService notifyService, TS3Properties properties) {
        this.repository = repository;
        this.notifyService = notifyService;
        this.properties = properties;
    }

    @Override
    public void notifyUserJoin(int id, String nickname) {
        TS3User user = repository.getUser(nickname);
        if (user == null || TimeUnit.MILLISECONDS.toMinutes(new Date().getTime())
                - TimeUnit.MILLISECONDS.toMinutes(user.createDate().getTime()) >= properties.getMessageTimeoutInMinutes()) {
            repository.saveUser(new TS3User(id, nickname, new Date()));
            notifyService.sendMessage(nickname);
        }
    }
}