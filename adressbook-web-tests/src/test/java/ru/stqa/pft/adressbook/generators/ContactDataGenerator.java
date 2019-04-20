package ru.stqa.pft.adressbook.generators;

import ru.stqa.pft.adressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);
    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getAddress(),
              contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone(),
              contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getGroup()));
    }
    writer.close();
  }


  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("firstname %s", i))
              .withLastname(String.format("lastname %s", i)).withAddress(String.format("address %s", i))
              .withHomePhone(String.format("home %s", i)).withMobile(String.format("mobile %s", i))
              .withWorkPhone(String.format("work %s", i))
              .withEmail(String.format("email %s", i)).withEmail2(String.format("email2 %s", i))
              .withEmail3(String.format("email3 %s", i)).withGroup(String.format("test %s", i)));
    }

    return contacts;
  }
}

