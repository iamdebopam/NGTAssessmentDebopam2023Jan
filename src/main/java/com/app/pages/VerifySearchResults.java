package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import org.testng.Assert;
public class VerifySearchResults{
    WebDriver driver;
    public VerifySearchResults(WebDriver driver){
        this.driver=driver;
    }
    public void verifyAndSearch() throws IOException {
        String rootFolder=System.getProperty("user.dir");
        Properties prop= new Properties();
        prop.load(new FileInputStream(rootFolder+"//src//main//resources//data.properties"));
        driver.get(prop.getProperty("url"));
        driver.findElement(By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[3]/input")).sendKeys(prop.getProperty("search"));
        driver.findElement(By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[3]/a/span")).click();
        String str=driver.getTitle();
        Assert.assertEquals(str,prop.getProperty("searchTitle"));
    }
}
