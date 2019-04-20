package ru.stqa.pft.adressbook.generators;

import ru.stqa.pft.adressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]); //параметр передает количество групп (преобразованный в число)
    File file = new File(args[1]);// параметр передает путь к файлу

    //генерация данных
    List<GroupData> groups = generateGroups(count);
    //сохранение этих данных в файл
    save(groups, file);
  }

  private static void save(List<GroupData> groups, File file) throws IOException {
    //сохранение списка в файл
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file); // запись
    for (GroupData group : groups) { // проходимся в цикле по всем группам
      writer.write(String.format("%s;%s;%s\n",group.getName(), group.getHeader(), group.getFooter())); // каждый записываем
    }
    writer.close(); //не забываем закрыть запись
  }

private static List<GroupData> generateGroups(int count) {
  List<GroupData>groups = new ArrayList<GroupData>();
  for (int i = 0; i < count; i++) {
    groups.add(new GroupData().withName(String.format("test %s",i))
            .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i))); // нумерованные группы
  }
  return groups;
}

}

