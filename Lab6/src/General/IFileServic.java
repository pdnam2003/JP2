package General;

import Entity.StatisticsView;
import Service.FileService;

import java.util.List;

public interface IFileServic <T>{
  public  List<T> readFile(String fileInPath);
  public List<T> writeFile(String fileOutPath);

}
