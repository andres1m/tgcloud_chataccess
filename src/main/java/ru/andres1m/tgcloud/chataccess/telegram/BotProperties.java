package ru.andres1m.tgcloud.chataccess.telegram;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "telegram.bot")
@Data
@PropertySource("classpath:application.properties")
public class BotProperties {
    private String token;
    private String chat;
    private String name;
}