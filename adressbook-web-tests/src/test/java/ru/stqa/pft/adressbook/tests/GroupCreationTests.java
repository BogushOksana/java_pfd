package ru.stqa.pft.adressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>(); //заполняем список массивов, каждый массив содержит набор данных для одного запуска. Сколько наборов, столько раз и запускается тест
    list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator(); // возвращается итератор списка
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() throws Exception {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test'");
    app.group().create(group);
    assertThat(app.group().count(),equalTo( before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
