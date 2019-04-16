package ru.stqa.pft.adressbook.model;

import java.util.Objects;

public class ContactData {
  private int id = Integer.MAX_VALUE;;
  private String firstname;
  private String lastname;
  private String nickname;
  private String mobile;
  private String home;
  private String work;
  private String email;
  private String group;

  public ContactData() {
  }

  public String getFirstname() {
    return firstname;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withHomePhone(String home) {
    this.home = home;
    return this;
  }

  public ContactData withWorkPhone(String work) {
    this.work = work;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getMobile() {
    return mobile;
  }

  public String getHomePhone() { return home; }

  public String getWorkPhone() { return work; }

  public String getEmail() { return email; }

  public String getGroup() { return group; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(email, that.email) &&
            Objects.equals(home, that.home) &&
            Objects.equals(work, that.work);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, mobile, email, home, work);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", mobile='" + mobile + '\'' +
            ", email='" + email + '\'' +
            ", home='" + home + '\'' +
            ", work='" + work + '\'' +
            '}';
  }

}

