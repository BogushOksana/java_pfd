package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().HomePage(); //переход на главную страницу
    ContactData contact = app.contact().all().iterator().next(); // загрузка списка множества контактов, выбор контакта случайным образом
    ContactData contactInfoFromEditFrom = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditFrom.getHomePhone())));
    assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditFrom.getMobile())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditFrom.getWorkPhone())));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
