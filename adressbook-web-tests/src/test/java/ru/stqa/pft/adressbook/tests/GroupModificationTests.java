package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.*;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupVoidModification() {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getId(), "test1", "test2", "test3");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
