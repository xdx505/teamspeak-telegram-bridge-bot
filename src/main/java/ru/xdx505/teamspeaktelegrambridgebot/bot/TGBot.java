package ru.xdx505.teamspeaktelegrambridgebot.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xdx505.teamspeaktelegrambridgebot.DateTimeUtils;
import ru.xdx505.teamspeaktelegrambridgebot.config.TGProperties;
import ru.xdx505.teamspeaktelegrambridgebot.config.TS3Properties;
import ru.xdx505.teamspeaktelegrambridgebot.service.TGNotifyService;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TGBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final TGProperties tgProperties;
    private final TS3Properties ts3Properties;
    private final TGNotifyService tgNotifyService;


    @Override
    public void consume(Update update) {
        if (update.getMessage() != null) {
            //register chat in memory
            Long chatId = update.getMessage().getChatId();
            tgProperties.getChatIds().add(chatId);

            //filter old updates by timeout
            Date updateDate = new Date((long) update.getMessage().getDate() * 1000);
            boolean isIntervalMoreThanMinutes =
                    DateTimeUtils.isIntervalMoreThanMinutes(updateDate, new Date(),
                            ts3Properties.getMessageTimeoutInMinutes());

            if (update.getMessage().getText() != null && update.getMessage().getText().startsWith("/")
                    && !isIntervalMoreThanMinutes) {
                String command = update.getMessage().getText().split("@")[0];
                switch (command) {
                    case "/info": {
                        tgNotifyService.sendInfoMessage(chatId);
                        break;
                    }
                    case "/list": {
                        break;
                    }

                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return tgProperties.getToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}
