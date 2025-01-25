package ru.xdx505.teamspeaktelegrambridgebot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.xdx505.teamspeaktelegrambridgebot.DateTimeUtils;
import ru.xdx505.teamspeaktelegrambridgebot.config.TS3Properties;
import ru.xdx505.teamspeaktelegrambridgebot.entity.TS3User;
import ru.xdx505.teamspeaktelegrambridgebot.repository.TS3UserRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TS3UserServiceImpl implements TS3UserService {
    private final TS3UserRepository repository;
    private final TGNotifyService notifyService;
    private final TS3Properties ts3Properties;

    @Override
    public void notifyUserJoin(int id, String nickname) {
        TS3User user = repository.getUser(nickname);
        Date now = new Date();
        if (user == null || DateTimeUtils.isIntervalMoreThanMinutes(user.createDate(), now,
                ts3Properties.getMessageTimeoutInMinutes())) {
            repository.saveUser(new TS3User(id, nickname, now));
            notifyService.sendUserJoinedMessage(nickname);
        }
    }
}