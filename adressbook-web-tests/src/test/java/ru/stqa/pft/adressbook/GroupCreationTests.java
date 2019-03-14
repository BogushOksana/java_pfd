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
    login("admin", "secret");
  }

  private void login(String username, String password) {
    vd.findElement(By.name("user")).clear();
    vd.findElement(By.name("user")).sendKeys(username);
    vd.findElement(By.name("pass")).clear();
    vd.findElement(By.name("pass")).sendKeys(password);
    vd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    gotoGroupPage();
    returnToGroupPage();
  }

  private void returnToGroupPage() {
    vd.findElement(By.linkText("Logout")).click();
  }

  private void submitGroupCreation() {
    vd.findElement(By.name("submit")).click();
  }

  private void fillGroupForm(GroupData groupData) {
    vd.findElement(By.name("group_name")).click();
    vd.findElement(By.name("group_name")).clear();
    vd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    vd.findElement(By.name("group_header")).clear();
    vd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    vd.findElement(By.name("group_footer")).clear();
    vd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  private void initGroupCreation() {
    vd.findElement(By.name("new")).click();
  }

  private void gotoGroupPage() {
    vd.findElement(By.linkText("groups")).click();
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
