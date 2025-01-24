package ru.xdx505.teamspeaktelegrambridgebot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.xdx505.teamspeaktelegrambridgebot.config.TGProperties;

@Slf4j
@Service
public class TGNotifyServiceImpl implements TGNotifyService {
    private final TelegramClient telegramClient;
    private final TGProperties properties;

    public TGNotifyServiceImpl(TelegramClient telegramClient, TGProperties properties) {
        this.telegramClient = telegramClient;
        this.properties = properties;
    }

    @Override
    public void sendMessage(String nickname) {
        String message = properties.getUserJoinedMessage().replaceAll("%nickname%", nickname);

        properties.getChatIds().forEach(chatId -> {
            try {
                telegramClient.execute(SendMessage.builder()
                        .chatId(chatId)
                        .text(message)
                        .build()
                );
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        });
    }
}
