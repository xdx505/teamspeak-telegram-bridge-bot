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
import ru.xdx505.teamspeaktelegrambridgebot.config.TS3Properties;

import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Component
public class TGBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final TGProperties properties;
    private final TS3Properties ts3Properties;
    private final TS3Api ts3Api;
    private final TelegramClient telegramClient;

    public TGBot(TGProperties properties, TS3Properties ts3Properties, TS3Api ts3Api) {
        this.properties = properties;
        if (properties.getChatIds() == null) {
            properties.setChatIds(new HashSet<>());
        }
        this.ts3Properties = ts3Properties;
        this.telegramClient = new OkHttpTelegramClient(properties.getToken());
        this.ts3Api = ts3Api;
    }

    @Override
    public void consume(Update update) {
        if (update.getMessage() != null) {

            Long chatId = update.getMessage().getChatId();
            properties.getChatIds().add(chatId);

            Date updateDate = new Date((long) update.getMessage().getDate() * 1000);
            Date currentDate = new Date();
            boolean isExpired = TimeUnit.MILLISECONDS.toMinutes(currentDate.getTime())
                    - TimeUnit.MILLISECONDS.toMinutes(updateDate.getTime())
                    >= ts3Properties.getMessageTimeoutInMinutes();

            if (update.getMessage().getText() != null && update.getMessage().getText().startsWith("/") && !isExpired) {
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
