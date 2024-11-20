package ru.andres1m.tgcloud.chataccess.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.AfterBotRegistration;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class ChatAccessBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;

    private final BotProperties botProperties;

    @Autowired
    public ChatAccessBot(BotProperties botProperties) {
        this.botProperties = botProperties;
        this.telegramClient = new OkHttpTelegramClient(getBotToken());
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {

    }

    //TODO логирование
    public File executeFile(GetFile file){
        try {
            return telegramClient.execute(file);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    //TODO логирование
    public Message sendDocument(InputFile inpFile) {
        SendDocument document = SendDocument
                .builder()
                .chatId(botProperties.getChat())
                .document(inpFile)
                .build();

        Message message;

        try {
            message = telegramClient.execute(document);
        } catch (TelegramApiException e) {
            message = null;
            e.printStackTrace();
        }
        return message;
    }

    //TODO логирование
    @AfterBotRegistration
    public void afterRegistration(BotSession botSession) {
        System.out.println("Registered bot running state is: " + botSession.isRunning());
    }
}