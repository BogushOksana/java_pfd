package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.goTo().ContactPage();
      app.contact().create(new ContactData()
              .withFirstname("Иван").withLastname("Иванов").withNickname("Ваня")
              .withHomePhone("111").withMobile("123456789").withWorkPhone("333").withEmail("ivanov@mail")
              .withEmail2("ivanov2@mail").withEmail3("ivanov3@mail"));}
  }
    @Test
    public void testContactMail() {
      app.goTo().HomePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getEmail(), equalTo(cleaned(contactInfoFromEditForm.getEmail())));
      assertThat(contact.getEmail2(), equalTo(cleaned(contactInfoFromEditForm.getEmail2())));
      assertThat(contact.getEmail3(), equalTo(cleaned(contactInfoFromEditForm.getEmail3())));
    }

    public String cleaned (String email){
      return email.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
  }