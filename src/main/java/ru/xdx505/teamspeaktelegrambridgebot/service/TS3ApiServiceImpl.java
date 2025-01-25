package ru.xdx505.teamspeaktelegrambridgebot.service;

import com.github.theholywaffle.teamspeak3.TS3Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TS3ApiServiceImpl implements TS3ApiService {
    private final TS3Api ts3Api;
}
