package ru.stqa.pft.adressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactsCreationTests {
  private WebDriver md;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    md = new FirefoxDriver();
    md.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testContactsCreation() throws Exception {
    md.get("http://localhost/addressbook/");
    md.findElement(By.name("user")).clear();
    md.findElement(By.name("user")).sendKeys("admin");
    md.findElement(By.name("pass")).click();
    md.findElement(By.name("pass")).clear();
    md.findElement(By.name("pass")).sendKeys("secret");
    md.findElement(By.xpath("//input[@value='Login']")).click();
    md.findElement(By.linkText("add new")).click();
    md.findElement(By.name("firstname")).click();
    md.findElement(By.name("firstname")).clear();
    md.findElement(By.name("firstname")).sendKeys("Иванов");
    md.findElement(By.name("lastname")).click();
    md.findElement(By.name("lastname")).clear();
    md.findElement(By.name("lastname")).sendKeys("Иван");
    md.findElement(By.name("nickname")).click();
    md.findElement(By.name("nickname")).clear();
    md.findElement(By.name("nickname")).sendKeys("Ваня");
    md.findElement(By.name("mobile")).click();
    md.findElement(By.name("mobile")).clear();
    md.findElement(By.name("mobile")).sendKeys("123456789");
    md.findElement(By.name("email")).click();
    md.findElement(By.name("email")).clear();
    md.findElement(By.name("email")).sendKeys("ivanov@mail");
    md.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
    md.findElement(By.linkText("Logout")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    md.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      md.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      md.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
