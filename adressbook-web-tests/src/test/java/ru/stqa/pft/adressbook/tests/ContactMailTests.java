package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

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
                .withAddress("Пермь")
                .withHomePhone("111").withMobile("123456789").withWorkPhone("333")
                // .withPhoto(new File("src/test/resources/123.png"))
                .withEmail("ivanov@mail").withEmail2("ivanov12@mail").withEmail3("ivanov3@mail").withGroup("test1"));
    }
  }

  @Test
  public void testContactMail() {
    app.goTo().HomePage(); //переход на главную страницу
    ContactData contact = app.contact().all().iterator().next(); // загрузка списка множества контактов, выбор контакта случайным образом
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm))); }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactMailTests::cleaned)
            .collect(Collectors.joining("\n")); }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "").replaceAll("[-()]", "");

  }
}
