package ru.xdx505.teamspeaktelegrambridgebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.xdx505.teamspeaktelegrambridgebot.config.TGProperties;
import ru.xdx505.teamspeaktelegrambridgebot.config.TS3Properties;

@SpringBootApplication
@EnableConfigurationProperties({TS3Properties.class, TGProperties.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
