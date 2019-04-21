package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {

    List<Object[]> list = new ArrayList<Object[]>(); //заполняем список массивов, каждый массив содержит набор данных для одного запуска. Сколько наборов, столько раз и запускается тест
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
              .withHomePhone(split[3]).withMobile(split[4]).withWorkPhone(split[5])
              .withEmail(split[6]).withEmail2(split[7]).withEmail3(split[8]).withGroup(split[9])});

      line = reader.readLine();
    }
    return list.iterator(); // возвращается итератор списка
  }

  @Test (dataProvider = "validContacts")
  public void testContactsCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/123.png");
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
