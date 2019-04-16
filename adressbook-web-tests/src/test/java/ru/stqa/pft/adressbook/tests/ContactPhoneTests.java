package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones(){
    app.goTo().HomePage(); //переход на главную страницу
    ContactData contact = app.contact().all().iterator().next(); // загрузка списка множества контактов, выбор контакта случайным образом
    ContactData contactInfoFromEditFrom = app.contact().infoFromEditForm(contact);

  }
}
