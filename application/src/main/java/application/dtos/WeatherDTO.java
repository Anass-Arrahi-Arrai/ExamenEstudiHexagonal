package application.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class WeatherDTO {
    private String cityName;
    private List<WeatherForecastDTO> forecasts;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<WeatherForecastDTO> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<WeatherForecastDTO> forecasts) {
        this.forecasts = forecasts;
    }

    public static class WeatherForecastDTO {
        LocalDateTime dateTime;
        double temperature;

        String description;

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
