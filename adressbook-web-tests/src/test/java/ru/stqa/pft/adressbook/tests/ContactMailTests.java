package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase {

  @Test
  public void testContactMail(){
    app.goTo().HomePage(); //переход на главную страницу
    ContactData contact = app.contact().all().iterator().next(); // загрузка списка множества контактов, выбор контакта случайным образом
    ContactData contactInfoFromEditFrom = app.contact().infoFromEditForm(contact);

    assertThat(contact.getEmail(), equalTo(contactInfoFromEditFrom.getEmail()));
    assertThat(contact.getEmail2(), equalTo(contactInfoFromEditFrom.getEmail2()));
    assertThat(contact.getEmail3(), equalTo(contactInfoFromEditFrom.getEmail3()));

  }
}
