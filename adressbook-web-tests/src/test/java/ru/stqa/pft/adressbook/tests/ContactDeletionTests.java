package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().ContactPage();
      app.contact().create(new ContactData()
              .withFirstname("Иван").withLastname("Иванов").withNickname("Ваня")
              .withAddress("Пермь")
              .withHomePhone("111").withMobile("123456789").withWorkPhone("333")
             // .withPhoto(new File("src/test/resources/123.png"))
              .withEmail("ivanov@mail").withEmail2("ivanov12@mail").withEmail3("ivanov3@mail").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts(); // метод all возвращает объект типа Contacts
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.closeAsert();
    app.goTo().HomePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));// меняем проверку
  }
}
