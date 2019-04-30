package ru.stqa.pft.adressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class); // должен обработать аннотации
      List<ContactData> contacts = (List<ContactData>)xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();// возвращается итератор списка (функциональное тестирование)
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); // тоже самое, что - <List<GroupData>.class
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();// возвращается итератор списка (функциональное тестирование)
    }
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactsCreation(ContactData contact) throws Exception {
    Contacts before = app.db().contacts();
   // File photo = new File("src/test/resources/123.png");
    app.goTo().ContactPage();
    app.contact().create(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactsCreation() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().ContactPage();
    ContactData contact = new ContactData()
            .withFirstname("Иван").withLastname("Иванов").withNickname("Ваня")
            .withAddress("Пермь")
            .withHomePhone("111").withMobile("123456789").withWorkPhone("333")
            .withEmail("ivanov@mail").withEmail2("ivanov12@mail").withEmail3("ivanov3@mail").withGroup("test1");
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
