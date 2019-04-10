package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().list().size() ==0) {
      app.goTo().ContactPage();
      app.contact().create(new ContactData("Иванов", "Иван", "Ваня", "123456789", "ivanov@mail", "test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    //app.getContactHelper().selectContact ();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Иванов", "Иван", "Ваня", "123456789", "ivanov@mail", null);
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

