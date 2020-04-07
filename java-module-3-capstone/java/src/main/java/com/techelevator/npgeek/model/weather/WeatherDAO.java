package com.techelevator.npgeek.model.weather;

import java.util.List;

public interface WeatherDAO {	

//public Weather getWeatherByParkCode(String parkCode);


public Weather getTodayWeather(String parkCode); 
public List<Weather> nextFour(String parkCode);
public Weather getTodayWeatherCelcius(String parkCode);
public List<Weather> nextFourCelcius(String parkCode);
public int convertTempToCelcius(int tempFahrenheit);
public List<Weather> fiveDayForecast(String parkCode, String tempChoice);

	
}
