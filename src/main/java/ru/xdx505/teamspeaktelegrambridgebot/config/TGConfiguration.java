package ru.xdx505.teamspeaktelegrambridgebot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class TGConfiguration {
    @Bean
    public TelegramClient telegramClient(TGProperties tgProperties) {
        return new OkHttpTelegramClient(tgProperties.getToken());
    }
}