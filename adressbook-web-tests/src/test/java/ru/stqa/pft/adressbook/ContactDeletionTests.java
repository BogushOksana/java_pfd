package ru.stqa.pft.adressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import static org.testng.Assert.assertTrue;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    gotoHomePage();
    selectContact();
    deleteSelectedContacts();
    closeAsert();
    gotoHomePage();
  }
}
