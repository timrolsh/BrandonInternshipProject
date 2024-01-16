package com.example.project.service.implementation;

import org.springframework.stereotype.Service;

import com.example.project.model.TextInput;
import com.example.project.repo.TextInputRepository;
import com.example.project.service.TextInputService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class TextInputServiceImplementation implements TextInputService {

    @Override
    public void addTextInput(String text, long time, String username) {
        log.info("Saving new text: {}", text);
        TextInputRepository.addTextInput(text, time, username);
    }

    @Override
    public void deleteTextInput(long time) {
        log.info("Deleting Text Input by ID: {}", time);
        TextInputRepository.deleteTextInput(time);
    }

    @Override
    public TextInput[] loadTextInputs() {
        log.info("Fetching all Text Inputs");
        return TextInputRepository.loadTextInputs();
    }

   @Override
    public TextInput[] loadTextInputsByUser(Long userId) {
        log.info("Fetching Text Inputs by ID: {}", userId);
        return TextInputRepository.loadTextInputsByUser(userId);
    }

    @Override
    public TextInput[] loadTextInputsByContent(String content) {
        log.info("Fetching Text Inputs by content: {}", content);
        return TextInputRepository.loadTextInputsByContent(content);
    }

    @Override
    public TextInput[] loadTextInputsByTime(long startTime, long endTime) {
        log.info("Fetching Text Inputs by times: {}, {}", startTime, endTime);
        
        return TextInputRepository.loadTextInputsByTime(startTime, endTime);
        
    }

}
