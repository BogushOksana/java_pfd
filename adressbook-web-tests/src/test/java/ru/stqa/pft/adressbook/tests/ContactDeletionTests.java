package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    app.gotoHomePage();
    app.selectContact();
    app.deleteSelectedContacts();
    app.closeAsert();
    app.gotoHomePage();
  }
}
