package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().selectContact();
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(new ContactData("Иванов", "Иван", "Ваня", "123456789", "ivanov@mail"));
      app.getContactHelper().submitContactModification();
      app.getGroupHelper().returnToGroupPage();
  }
}
