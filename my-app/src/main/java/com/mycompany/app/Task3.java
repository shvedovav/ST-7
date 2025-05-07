package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Task3 {
    public static void run() {
        System.setProperty("webdriver.chrome.driver", "E:\\Downloads\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms");
            WebElement element = driver.findElement(By.tagName("pre"));
            String json = element.getText();
            JSONObject obj = (JSONObject) new JSONParser().parse(json);
            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");
            try (PrintWriter writer = new PrintWriter(new FileWriter("../result/forecast.txt"))) {
                writer.printf("%-3s %-20s %-12s %-10s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
                for (int i = 0; i < times.size(); i++) {
                    writer.printf("%-3d %-20s %-12.1f %-10.2f%n", i + 1, times.get(i), temps.get(i), rains.get(i));
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            driver.quit();
        }
    }
}
