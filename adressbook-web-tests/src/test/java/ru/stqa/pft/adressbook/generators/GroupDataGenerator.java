package ru.stqa.pft.adressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.adressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator { //подключили библиотеку jcommander - не поддерживает работу с файлами напрямую

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;


  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();


  }



  private void run() throws IOException {
    //генерация данных
    List<GroupData> groups = generateGroups(count);
    //сохранение этих данных в файл
    save(groups, new File(file));
  }

  private void save(List<GroupData> groups, File file) throws IOException {
    //сохранение списка в файл
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file); // запись
    for (GroupData group : groups) { // проходимся в цикле по всем группам
      writer.write(String.format("%s;%s;%s\n",group.getName(), group.getHeader(), group.getFooter())); // каждый записываем
    }
    writer.close(); //не забываем закрыть запись
  }

private List<GroupData> generateGroups(int count) {
  List<GroupData>groups = new ArrayList<GroupData>();
  for (int i = 0; i < count; i++) {
    groups.add(new GroupData().withName(String.format("test %s",i))
            .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i))); // нумерованные группы
  }
  return groups;
}

}

