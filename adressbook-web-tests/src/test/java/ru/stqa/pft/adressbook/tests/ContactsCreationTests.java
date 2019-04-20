package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {

  @Test
  public void testContactsCreation() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().ContactPage();
    File photo = new File("src/test/resources/123.png");
    ContactData contact = new ContactData()
            .withFirstname("Иван").withLastname("Иванов").withNickname("Ваня").withPhoto(photo)
            .withMobile("123456789").withEmail("ivanov@mail").withGroup("test1");
    app.contact().create(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactsCreation() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().ContactPage();
    ContactData contact = new ContactData()
            .withFirstname("Иван'").withLastname("Иванов").withNickname("Ваня")
            .withMobile("123456789").withEmail("ivanov@mail").withGroup("test1");
    app.contact().create(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/123.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());



  }

}
