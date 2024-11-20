package ru.andres1m.tgcloud.chataccess.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.andres1m.tgcloud.chataccess.telegram.BotProperties;
import ru.andres1m.tgcloud.chataccess.telegram.ChatAccessBot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Component
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private ChatAccessBot bot;
    private BotProperties properties;

    @Override
    public String getFileLink(String id) {
        GetFile getFile = new GetFile(id);
        getFile.setFileId(id);
        File file = bot.executeFile(getFile);
        return String.format("https://api.telegram.org/file/bot%s/%s", properties.getToken(), file.getFilePath());
    }

    //TODO логирование
    @Override
    public String sendFile(String encodedFilePath) {
        InputFile file;
        try (InputStream is = new FileInputStream(encodedFilePath)) {
            file = new InputFile(is, "123");
            return bot.sendDocument(file).getDocument().getFileId();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}