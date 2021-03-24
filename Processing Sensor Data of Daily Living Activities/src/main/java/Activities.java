import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Activities {
    ArrayList<MonitoredData> activities;

    public Activities()
    {
        activities=new ArrayList<MonitoredData>();
    }

    public void addObject(MonitoredData act) {
        activities.add(act);
    }


    public int countDistinctDays()
    {
        TreeSet<Integer> times=new TreeSet<Integer>();
        activities.forEach(activ -> {
             times.add(activ.getDistinctDay(activ.getStart_time().getMonthValue(), activ.getStart_time().getDayOfMonth()));
        });
        int nr=times.size();
        return nr;
    }

    public Map<String, Long> countDifferentActivities()
    {
        Map<String, Long> differentActivities=new HashMap<String, Long>();
        differentActivities=activities.stream().collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting()));
        return differentActivities;
    }


    public Map<Integer, Map<String, Long>> howManyTimes()
    {
        Map<Integer, Map<String, Long>> times=new HashMap<Integer, Map<String, Long>>();
        activities.forEach(activ->{
            if(!times.containsKey(activ.getStart_time()))
            {
                Map<String, Long> oneDay=activities.stream().filter(t->t.getStart_time().getDayOfMonth()==activ.getStart_time().getDayOfMonth()).collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting()));
                times.put(activ.getStart_time().getDayOfMonth(), oneDay);
            }
        });
       return times;
    }


    public Map<String, Duration> computeEntireDuration()
   {
       Map<String, Duration> entireDuration=new HashMap<String, Duration>();
       activities.forEach(activ->{
           if(!entireDuration.containsKey(activ.getActivity_label()))
           {
               Duration duration=Duration.parse("PT0H0M0S");
               entireDuration.put(activ.getActivity_label(), duration);
               activities.forEach(activ2->
               {
                   if(activ2.getActivity_label().equals(activ.getActivity_label()))
                   {
                       Duration duration2=Duration.between(activ2.getStart_time(), activ2.getEnd_time());
                       entireDuration.put(activ.getActivity_label(), entireDuration.get(activ.getActivity_label()).plus(duration2));
                   }
               });
           }
       });
     return entireDuration;
   }

   public List<String> filterActivities()
   {
       List<String> names=new ArrayList<String>();
       Duration aux=Duration.parse("PT0H5M0S");
       TreeSet<String> namesOfActivities=new TreeSet<String>();
       activities.forEach(activ->{namesOfActivities.add(activ.getActivity_label());});
       namesOfActivities.forEach(name->{
           long count = activities.stream().filter(act -> name.equals(act.getActivity_label())).map(act -> Duration.between(act.getStart_time(), act.getEnd_time())).filter(duration -> duration.compareTo(aux) < 0).count();
           long totalCount=activities.stream().filter(t->t.getActivity_label().equals(name)).collect(Collectors.counting());
           long procent=(count/totalCount)*100;
           if(procent>90)
           {
               names.add(name);
           }
       });
       return names;
   }

   public ArrayList<MonitoredData> getActivities()
   {
       return activities;
   }


}
