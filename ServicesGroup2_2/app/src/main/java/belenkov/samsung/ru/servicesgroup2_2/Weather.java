package belenkov.samsung.ru.servicesgroup2_2;

import java.io.Serializable;

public class Weather implements Serializable {
    private String date;
    private String temp;
    private String humidity;

    public Weather(String date, String temp, String humidity) {
        this.date = date;
        this.temp = temp;
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
