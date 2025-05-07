package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void run() {
        System.setProperty("webdriver.chrome.driver", "E:\\Downloads\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://api.ipify.org/?format=json");
            WebElement element = driver.findElement(By.tagName("pre"));
            String json = element.getText();
            JSONObject obj = (JSONObject) new JSONParser().parse(json);
            System.out.println(obj.get("ip"));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            driver.quit();
        }
    }
}
