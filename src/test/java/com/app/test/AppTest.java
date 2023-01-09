package com.app.test;

import com.app.pages.CheckCart;
import com.app.pages.VerifySearchResults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class AppTest {
    WebDriver driver;
    VerifySearchResults verify;
    CheckCart check;

    @BeforeMethod
    public void setup() throws Exception {
        String rootFolder = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", rootFolder + "//src//test//resources//chromedriver.exe");
        driver = new ChromeDriver();

        verify = new VerifySearchResults(driver);
        check = new CheckCart(driver);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("script completed");
    }

    @Test
    public void verifySearchPage() throws IOException {
        verify.verifyAndSearch();
    }

    @Test
    public void verifyCheckCartPage() throws IOException, InterruptedException {
        check.checkBag();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
