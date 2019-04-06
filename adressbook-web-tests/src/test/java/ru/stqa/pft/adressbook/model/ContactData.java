package ru.stqa.pft.adressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstname;
  private final String lastname;
  private final String nickname;
  private final String mobile;
  private final String email;
  private String group;


  public ContactData (String firstname, String lastname, String nickname, String mobile, String email, String group) {
    this.id = 0;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.mobile = mobile;
    this.email = email;
    this.group = group;
  }
  public ContactData(int id, String firstname, String lastname, String nickname, String mobile, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.mobile = mobile;
    this.email = email;
    this.group = group;
  }



  public String getFirstname() {
    return firstname;
  }


  public void setId(int id) {
    this.id = id;
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

  public String getEmail() {
    return email;
  }

  public String getGroup() { return group; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}

