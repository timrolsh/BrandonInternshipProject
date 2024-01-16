package com.example.project.service;

import java.sql.Time;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.project.model.TextInput;

@Service
public interface TextInputService {
    TextInput[] loadTextInputs();
    TextInput[] loadTextInputsByUser(Long userId);
    TextInput[] loadTextInputsByContent(String content);
    TextInput[] loadTextInputsByTime(long startTime, long endTime);
    void addTextInput(String text, long time, String username);
    void deleteTextInput(long time);
   
}
