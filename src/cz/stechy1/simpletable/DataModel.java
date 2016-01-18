package cz.stechy1.simpletable;

/**
 * Created by Petr on 18. 6. 2015.
 */
public class DataModel {
    private String name;
    private String time;

    public DataModel(String name, String time) throws Exception {
        if(validateTime(time)) {
            this.name = name;
            this.time = time;
        } else
            throw new Exception("Čas není validní");
    }

    public static boolean validateTime(String time) {

        String[] parsedDime = time.split(":");
        if(parsedDime.length != 2)
            throw new IllegalArgumentException("Čas není zadán správně");
        String hourS = parsedDime[0];
        String minS = parsedDime[1];
        int hour, min;

        try {
            hour = Integer.parseInt(hourS);
            min = Integer.parseInt(minS);
        } catch(Exception ex) {
            throw new IllegalArgumentException("Čas není zadán správně");
        }

        if(hour > 24 || hour < 1 || min > 60 || min < 1)
            throw new IllegalArgumentException("Čas není ve správném formátu");

        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
