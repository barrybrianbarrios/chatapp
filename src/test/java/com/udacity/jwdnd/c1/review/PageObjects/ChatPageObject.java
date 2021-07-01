package com.udacity.jwdnd.c1.review.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPageObject {

    public enum messageTypeOptions {Say, Shout, Whisper};

    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(id = "messageType")
    private WebElement messageType;

    @FindBy(tagName = "h1")
    private WebElement displayedChatMessage;

    @FindBy(css = "input[type=\"submit\"]")
    private WebElement submit;

    public ChatPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setDisplayedMessage(String message) {
         messageText.sendKeys(message);
    }

    public WebElement getMessageTypeElement(){
        return messageType;
    }
    public String getDisplayedMessage(){
        return displayedChatMessage.getText();
    }

    public void sendButton(){ submit.submit();}


}
