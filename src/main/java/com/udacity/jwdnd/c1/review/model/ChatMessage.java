package com.udacity.jwdnd.c1.review.model;

public class ChatMessage {

    private String userName;
    private String messageText;
    private Integer messageId;



    public ChatMessage(String userName, String messageText) {
        this.userName = userName;
        this.messageText = messageText;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessageText() {
        return messageText;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
