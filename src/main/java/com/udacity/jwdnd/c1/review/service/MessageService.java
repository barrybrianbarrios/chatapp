package com.udacity.jwdnd.c1.review.service;
import com.udacity.jwdnd.c1.review.mapper.ChatMessageMapper;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MessageService {
    //private String message;
    //private List<ChatMessage> chatMessages;

    private final ChatMessageMapper chatMessageMapper;

    public MessageService(ChatMessageMapper chatMessageMapper) {
        this.chatMessageMapper = chatMessageMapper;
    }
    /*public MessageService(String message){
        System.out.println("MessageService");
        this.message = message;
    }*/
    List<String> BadWords = Arrays.asList("fuck", "bitch", "moron", "raping", "hell");
    /*
    @PostConstruct
    public void postConstruct() {
        this.chatMessages =  new ArrayList<>();
    }*/

    private boolean checkForBadMessages(ChatForm chatForm){
        if(chatForm.getUsername().isEmpty() || chatForm.getMessageText().isEmpty()){
            return true;
        }
        return BadWords.stream().anyMatch(s -> chatForm.getMessageText().toLowerCase().contains(s));
    }
    public void addMessage(ChatForm chatForm) {

        if(checkForBadMessages(chatForm)){
            return;
        }

        String _message;
        switch(chatForm.getMessageType()) {
            case "Shout":
                _message = chatForm.getMessageText().toUpperCase();
                break;
            case "Whisper":
                _message = chatForm.getMessageText().toLowerCase();
                break;
            default:
                _message = chatForm.getMessageText();
        }



        ChatMessage chatMessage = new ChatMessage(chatForm.getUsername(), _message);
        chatMessageMapper.insert(chatMessage);
        //chatMessages.add(chatMessage);
    }

    public List<ChatMessage> getMessages() {
        return chatMessageMapper.getMessages();
        //return new ArrayList<>(this.chatMessages);
    }

    /*
    public String uppercase(){
        return message.toUpperCase();
    }

    public String lowercase(){
        return message.toLowerCase();
    }
    */
}

