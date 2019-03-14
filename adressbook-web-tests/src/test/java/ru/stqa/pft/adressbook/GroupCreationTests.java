package ru.stqa.pft.adressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupCreationTests {
  private WebDriver vd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    vd = new FirefoxDriver();
    vd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    vd.get("http://localhost/addressbook/");
    vd.findElement(By.name("user")).clear();
    vd.findElement(By.name("user")).sendKeys("admin");
    vd.findElement(By.name("pass")).clear();
    vd.findElement(By.name("pass")).sendKeys("secret");
    vd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testGroupCreation() throws Exception {
    vd.findElement(By.linkText("groups")).click();
    vd.findElement(By.name("new")).click();
    vd.findElement(By.name("group_name")).click();
    vd.findElement(By.name("group_name")).clear();
    vd.findElement(By.name("group_name")).sendKeys("test1");
    vd.findElement(By.name("group_header")).clear();
    vd.findElement(By.name("group_header")).sendKeys("test2");
    vd.findElement(By.name("group_footer")).clear();
    vd.findElement(By.name("group_footer")).sendKeys("test3");
    vd.findElement(By.name("submit")).click();
    vd.findElement(By.linkText("groups")).click();
    vd.findElement(By.linkText("Logout")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    vd.quit();
    
  }

  private boolean isElementPresent(By by) {
    try {
      vd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      vd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
