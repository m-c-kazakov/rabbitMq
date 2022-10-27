package com.example.emitter.controller;

import com.example.emitter.dto.RequestDto;
import com.example.emitter.service.EmitterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {

    EmitterService emitterService;

    @PostMapping("/")
    public void sendToRabbit(@RequestBody RequestDto dto) {
        log.info("Мы приняли на контроллер: " + dto.toString());
        emitterService.sendMessage(dto);
    }
}
