package ru.xdx505.teamspeaktelegrambridgebot.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "teamspeak")
public class TS3Properties {
    private String queryAdminLogin;
    private String queryAdminPassword;
    private String botName;
    private String serverHost;
    private int serverPort;
    private int messageTimeoutInMinutes;
}
