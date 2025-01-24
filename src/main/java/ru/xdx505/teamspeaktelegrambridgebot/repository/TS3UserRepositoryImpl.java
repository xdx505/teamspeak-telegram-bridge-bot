package ru.xdx505.teamspeaktelegrambridgebot.repository;

import org.springframework.stereotype.Service;
import ru.xdx505.teamspeaktelegrambridgebot.entity.TS3User;

import java.util.HashMap;
import java.util.Map;

@Service
public class TS3UserRepositoryImpl implements TS3UserRepository {
    private final static Map<String, TS3User> users = new HashMap<>();

    @Override
    public TS3User getUser(String username) {
        return users.getOrDefault(username, null);
    }

    @Override
    public void saveUser(TS3User user) {
        users.put(user.nickname(), user);
    }

    @Override
    public void removeUser(Integer id) {
        users.values().stream()
                .filter(entry -> entry.id().equals(id))
                .findFirst()
                .ifPresentOrElse(entry -> users.remove(entry.nickname()), null);
    }
}
