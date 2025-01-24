package ru.xdx505.teamspeaktelegrambridgebot.config;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.xdx505.teamspeaktelegrambridgebot.listener.TS3ListenerImpl;

@Configuration
public class TS3Configuration {

    @Bean
    public TS3Config ts3Config(TS3Properties properties) {
        TS3Config config = new TS3Config();
        config.setHost(properties.getServerHost());
        return config;
    }

    @Bean
    public TS3Query ts3Query(TS3Config config) {
        TS3Query query = new TS3Query(config);
        query.connect();
        return query;
    }

    @Bean
    public TS3Api ts3Api(TS3Query query, TS3Properties properties) {
        TS3Api api = query.getApi();
        api.login(properties.getQueryAdminLogin(), properties.getQueryAdminPassword());
        api.selectVirtualServerByPort(properties.getServerPort());
        //TODO ник должен быть уникальным
        api.setNickname(properties.getBotName() + (int) ((Math.random() * (999 - 1)) + 1));
        api.registerAllEvents();
        return api;
    }
}
