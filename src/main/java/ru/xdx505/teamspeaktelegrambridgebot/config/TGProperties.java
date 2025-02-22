package ru.xdx505.teamspeaktelegrambridgebot.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "tg")
public class TGProperties {
    private String token;
    private Set<Long> chatIds;
    private Message message;

    public String getUserJoinedMessage() {
        return message.getUserJoined();
    }

    public String getServerInfoMessage() {
        return message.getServerInfo();
    }

    @Data
    @NoArgsConstructor
    public static class Message {
        private String serverInfo;
        private String userJoined;
    }
}
