package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class CheckCart {
    WebDriver driver;

    public CheckCart(WebDriver driver) {
        this.driver = driver;
    }

    public void checkBag() throws IOException, InterruptedException {
        String rootFolder = System.getProperty("user.dir");
        Properties prop = new Properties();
        prop.load(new FileInputStream(rootFolder + "//src//main//resources//data.properties"));
        driver.get(prop.getProperty("loginurl"));
        driver.findElement(By.xpath("//*[@id=\"mobileNumberPass\"]")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.xpath("//*[@id=\"reactPageContent\"]/div/div/form/div/div[2]/input")).sendKeys(prop.getProperty("password"));
        driver.findElement(By.xpath("//*[@id=\"reactPageContent\"]/div/div/form/div/div[3]/button")).click();
        Thread.sleep(35000);
        driver.findElement(By.xpath("//button[@class=\"btn primary  lg block submitButton\"]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[3]/input")).sendKeys(prop.getProperty("search1"));
        driver.findElement(By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[3]/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"desktopSearchResults\"]/div[2]/section/ul/li[1]/a/div[1]/div/div/div/picture/img")).click();

        //handling window handle
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> I1= allWindows.iterator();
        while(I1.hasNext())
        {
            String child_window=I1.next();
            if(!mainWindow.equals(child_window))
            {
                driver.switchTo().window(child_window);
            }}

        driver.findElement(By.xpath("//*[@id=\"sizeButtonsContainer\"]/div[2]/div[1]/div[1]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"mountRoot\"]/div/div[1]/main/div[2]/div[2]/div[2]/div[2]/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[2]/a[2]/span[3]")).click();
        String str1=driver.findElement(By.xpath("//*[@id=\"appContent\"]/div/div/div/div/div[1]/div[5]/div[2]")).getText();
        Assert.assertEquals(str1,prop.getProperty("check"));
    }
}