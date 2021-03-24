import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MainClass {

    public static void main(String[] args) {

        Path filePath = Paths.get("Activities.txt");
        Activities activities=new Activities();
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter("Task_1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (Stream<String> stream = Files.lines( filePath ))
        {
             stream.forEach(s->{
                 LocalDateTime start_time=null, end_time=null;
                 String[] vector=s.split("\t");
                 DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                 start_time=LocalDateTime.parse(vector[0], formatter);
                 end_time=LocalDateTime.parse(vector[2], formatter);
                 activities.addObject(new MonitoredData(start_time, end_time,vector[4]));
             });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        for(MonitoredData dates: activities.getActivities())
        {
            fileWriter.println(dates.toString());
        }
        fileWriter.close();


        int count=activities.countDistinctDays();
        PrintWriter fileWriter2 = null;
        try {
            fileWriter2 = new PrintWriter("Task_2.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fileWriter2.println("The number of the distinct days that appear in the monitoring data:");
        fileWriter2.println(count);
       fileWriter2.close();


       Map<String, Long> task3=activities.countDifferentActivities();
        PrintWriter fileWriter3 = null;
        try {
            fileWriter3 = new PrintWriter("Task_3.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fileWriter3.println("How many times each activity has appeared over the entire monitoring period:");
        PrintWriter finalFileWriter = fileWriter3;
        task3.entrySet().forEach(entry->{
            finalFileWriter.println(entry.getKey() + " " + entry.getValue());
        });
        fileWriter3.close();


        Map<Integer, Map<String, Long>> task4=activities.howManyTimes();
        PrintWriter fileWriter4 = null;
        try {
            fileWriter4 = new PrintWriter("Task_4.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fileWriter4.println("how many times each activity has appeared for each day over the monitoring period:");
        PrintWriter finalFileWriter1 = fileWriter4;
        task4.entrySet().forEach(entry->{
            finalFileWriter1.print(entry.getKey()+": ");
            entry.getValue().entrySet().forEach(entry2->{
                finalFileWriter1.println(" "+entry2.getKey()+": "+ entry2.getValue());
            });
            finalFileWriter1.println();
        });
        fileWriter4.close();


        Map<String, Duration> task5=activities.computeEntireDuration();
        PrintWriter fileWriter5 = null;
        try {
            fileWriter5 = new PrintWriter("Task_5.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter finalFileWriter2 = fileWriter5;

        fileWriter5.println("For each activity, the entire duration over the monitoring period is");
        task5.entrySet().forEach(entry->{
            finalFileWriter2.println(entry.getKey() + " " + entry.getValue());
        });
        fileWriter5.close();


        List<String> names=activities.filterActivities();
        PrintWriter fileWriter6 = null;
        try {
            fileWriter6 = new PrintWriter("Task_6.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter finalFileWriter3 = fileWriter6;

        fileWriter6.println("The activities that have more than 90% of the monitoring records with duration less than 5 minutes:");
        names.forEach(entry->{
            finalFileWriter3.println("->"+entry);
        });
        fileWriter6.close();


    }

}
