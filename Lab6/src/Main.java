import Entity.CRStatistics;
import Entity.StatisticsView;
import Service.FileService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String fileInPathStatistics = System.getProperty("user.dir");
        String fileInPath =fileInPathStatistics.replace("/","\\")+"/data/Statics.view.txt";
        FileService fileService = new FileService();
        List<StatisticsView> statisticsViews = fileService.readFile(fileInPath);
        //data analytics
        Map<CRStatistics,CRStatistics> dataCRS =  statisticsViews.stream()
                .collect(Collectors.groupingBy(
                        cr->new CRStatistics(cr.getId(), cr.getMonthOfDate(), cr.getYearOfDate()
                ))
//                .collect(Collectors.groupingBy(
//                   cr-> new CRStatistics(cr.),
//                    Collectors.collectingAndThen(
//                            Collectors.toList(),
//                            ListCR->{
//                                CRStatistics crStatistics = new CRStatistics();
//                                StatisticsView firsStatistics = listCR.getFirst();
//                                int totalVIew = ListCR.stream()
//                                        .mapToInt(StatisticsView :: getView).sum();
//                                crStatistics.setId(firsStatistics.getId());
//                                crStatistics.setMonth(firsStatistics.getMonthOfDate());
//                                crStatistics.setYear(firsStatistics.getYearOfDate());
//                                crStatistics.setTotalView(totalVIew);
//                                crStatistics.setTotalAddToCart(ListCR.stream().mapToInt(StatisticsView::getAddToCart).sum());
//                                        return crStatistics;
//
//                            }
//                    ),


                ));

    }
}