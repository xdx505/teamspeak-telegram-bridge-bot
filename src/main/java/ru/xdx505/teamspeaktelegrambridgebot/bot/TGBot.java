package ru.xdx505.teamspeaktelegrambridgebot.bot;

import com.github.theholywaffle.teamspeak3.TS3Api;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.xdx505.teamspeaktelegrambridgebot.config.TGProperties;

import java.util.HashSet;

@Component
public class TGBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final TGProperties properties;
    private final TS3Api ts3Api;
    private final TelegramClient telegramClient;

    public TGBot(TGProperties properties, TS3Api ts3Api) {
        this.properties = properties;
        if (properties.getChatIds() == null) {
            properties.setChatIds(new HashSet<>());
        }
        this.telegramClient = new OkHttpTelegramClient(properties.getToken());
        this.ts3Api = ts3Api;
    }

    @Override
    public void consume(Update update) {
        if (update.getMessage() != null) {
            Long chatId = update.getMessage().getChatId();
            properties.getChatIds().add(chatId);
            if (update.getMessage().getText() != null && update.getMessage().getText().startsWith("/")) {
                String command = update.getMessage().getText().split("@")[0];
                try {
                    switch (command) {
                        case "/info": {
                            telegramClient.execute(SendMessage.builder()
                                    .chatId(chatId)
                                    .text(properties.getServerInfoMessage())
                                    .parseMode("MarkdownV2")
                                    .build());
                            break;
                        }
                        case "/list": {
                            break;
                        }

                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return properties.getToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}
