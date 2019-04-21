package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContacts() {
    File photo = new File("src/test/resources/123.png");
    List<Object[]> list = new ArrayList<Object[]>(); //заполняем список массивов, каждый массив содержит набор данных для одного запуска. Сколько наборов, столько раз и запускается тест
    list.add(new Object[] {new ContactData().withFirstname("Иван").withLastname("Иванов").withNickname("Ваня")
            .withEmail("ivanov@mail").withMobile("123456789").withGroup("test1").withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("Иван").withLastname("Иванов ").withNickname("Ваня")
            .withEmail("ivanov@mail ").withMobile("123456789").withGroup("test1").withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("Иван").withLastname("Иванов").withNickname("Ваня")
            .withEmail("ivanov@mail").withMobile("123456789").withGroup("test1").withPhoto(photo)});
    return list.iterator(); // возвращается итератор списка
  }

  @Test (dataProvider = "validContacts")
  public void testContactsCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.goTo().ContactPage();
    app.contact().create(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
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
