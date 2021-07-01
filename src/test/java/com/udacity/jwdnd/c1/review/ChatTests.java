package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.PageObjects.ChatPageObject;
import com.udacity.jwdnd.c1.review.PageObjects.LoginPageObject;
import com.udacity.jwdnd.c1.review.PageObjects.SignUpPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatTests {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private ChatPageObject chatPage;
    private LoginPageObject loginPage;
    private SignUpPageObject signupPage;
    private static WebDriverWait wait;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }



    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPageObject(driver);
        signupPage = new SignUpPageObject(driver);
        chatPage = new ChatPageObject(driver);
    }

    @Test
    public void testLoginSignupandChat() {
        wait.until(webDriver -> webDriver.findElement(By.id("signup-link")));
        loginPage.Register();
        wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));
        wait.until(webDriver -> webDriver.findElement(By.id("inputUsername")));
        signupPage.setInputFirstName("James");
        signupPage.setInputLastName("Banks");
        String username = "jbanks";
        signupPage.setInputUsername(username);
        String password = "jbanks123";
        signupPage.setInputPassword(password);
        signupPage.submit();
        System.out.println("login-link b4");
        wait.until(webDriver -> webDriver.findElement(By.id("login-link")));
        System.out.println("login-link after");
        signupPage.clickLoginLink();
        System.out.println("click Login Link");
        wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));
        wait.until(webDriver -> webDriver.findElement(By.id("inputUsername")));
        wait.until(webDriver -> webDriver.findElement(By.id("inputPassword")));
        loginPage.setInputUsername(username);
        loginPage.setInputPassword(password);
        loginPage.Login();
        wait.until(webDriver -> webDriver.findElement(By.id("messageText")));
        String message = "Barry Stop Cheating!";
        chatPage.setDisplayedMessage(message);
        Select messageTypeDropdown = new Select(chatPage.getMessageTypeElement());
        messageTypeDropdown.selectByVisibleText(ChatPageObject.messageTypeOptions.Shout.toString());
        chatPage.sendButton();
        assertEquals(username + " : " + message.toUpperCase(), chatPage.getDisplayedMessage());
    }






}
