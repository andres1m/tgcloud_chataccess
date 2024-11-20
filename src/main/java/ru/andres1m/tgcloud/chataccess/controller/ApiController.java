package ru.andres1m.tgcloud.chataccess.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.andres1m.tgcloud.chataccess.service.MessageService;

import java.util.Map;

@RestController
@RequestMapping("/tgcloud/chataccess/api/")
@AllArgsConstructor
public class ApiController {
    private MessageService service;

    @GetMapping("/getFileByFileId")
    public Map<String, Object> getFileByMessageId(@RequestParam("id") String id) {
        return Map.of(
                "result", service.getFileLink(id)
        );
    }

    @PostMapping("/sendFile")
    public Map<String, Object> sendFile(@RequestBody Map<String, String> jsonParams){
        return Map.of(
                "result", service.sendFile(jsonParams.get("encodedFilePath"))
        );
    }
}