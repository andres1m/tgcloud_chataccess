package ru.andres1m.tgcloud.chataccess.service;

public interface MessageService {
    String getFileLink(String id);
    String sendFile(String encodedFile);
}