package ru.xdx505.teamspeaktelegrambridgebot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.xdx505.teamspeaktelegrambridgebot.config.TGProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class TGNotifyServiceImpl implements TGNotifyService {
    private final TelegramClient telegramClient;
    private final TGProperties tgProperties;

    @Override
    public void sendUserJoinedMessage(String nickname) {
        String message = tgProperties.getUserJoinedMessage().replaceAll("%nickname%", nickname);
        tgProperties.getChatIds().forEach(chatId -> {
            try {
                telegramClient.execute(SendMessage.builder()
                        .chatId(chatId)
                        .text(message)
                        .parseMode("MarkdownV2")
                        .build()
                );
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        });
    }

    @Override
    public void sendInfoMessage(long chatId) {
        try {
            telegramClient.execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(tgProperties.getServerInfoMessage())
                    .parseMode("MarkdownV2")
                    .build());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
