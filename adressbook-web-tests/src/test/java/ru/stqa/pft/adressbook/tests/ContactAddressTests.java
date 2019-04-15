package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.goTo().ContactPage();
      app.contact().create(new ContactData()
              .withFirstname("Иван").withLastname("Иванов").withNickname("Ваня")
              .withAddress("Пермь").withHomePhone("111").withMobile("123456789").withWorkPhone("333")
              .withEmail("ivanov@mail").withEmail2("ivanov2@mail").withEmail3("ivanov3@mail"));}
  }

  @Test
  public void testContactMail() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
  }

  public static String cleaned (String address) {
    return address.replaceAll("\\s", "").replaceAll("[-()]", "");

  }
}