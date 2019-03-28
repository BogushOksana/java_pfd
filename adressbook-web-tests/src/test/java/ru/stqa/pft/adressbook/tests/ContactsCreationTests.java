package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactsCreationTests extends TestBase{

  @Test
  public void testContactsCreation() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().createContact(new ContactData("Иванов", "Иван", "Ваня", "123456789", "ivanov@mail", "test1"), true);
  }
}
