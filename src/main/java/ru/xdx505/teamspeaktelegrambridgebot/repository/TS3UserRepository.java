package ru.xdx505.teamspeaktelegrambridgebot.repository;

import ru.xdx505.teamspeaktelegrambridgebot.entity.TS3User;

public interface TS3UserRepository {
    TS3User getUser(String username);

    void saveUser(TS3User username);

    void removeUser(Integer id);
}
