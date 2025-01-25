package ru.xdx505.teamspeaktelegrambridgebot.service;

public interface TGNotifyService {
    void sendUserJoinedMessage(String nickname);
    void sendInfoMessage(long chatId);
}
