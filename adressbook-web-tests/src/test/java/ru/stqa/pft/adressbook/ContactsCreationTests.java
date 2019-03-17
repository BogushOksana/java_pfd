package ru.stqa.pft.adressbook;

import org.testng.annotations.Test;

public class ContactsCreationTests extends TestBase{

  @Test
  public void testContactsCreation() throws Exception {
    gotoContactPage();
    initContactCreation();
    fillContactForm(new ContactData("Иванов", "Иван", "Ваня", "123456789", "ivanov@mail"));
    submitContactCreation();
    returnToGroupPage();
  }
}
