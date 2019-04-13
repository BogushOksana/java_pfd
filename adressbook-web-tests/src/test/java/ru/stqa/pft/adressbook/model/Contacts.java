package ru.stqa.pft.adressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate; // delegate -здесь атрибут

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate); // дубль с новым множеством и с этими же элемнтами, присвоение нового множества в качестве атрибута в новый создаваемый конструктором объект
  }

  public Contacts() {
    this.delegate = new HashSet<>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return null;
  }

  public Contacts withAdded(ContactData contact){  //метод добавляет контакт
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  public Contacts without(ContactData contact){   //второй метод-копия который удаляет какой-то контакт
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
