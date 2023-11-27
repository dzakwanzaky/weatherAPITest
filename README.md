# OpenWeatherMap API Test Automation

This repository contains TestNG-based API automation tests for OpenWeatherMap using RestAssured. The tests cover three main scenarios related to weather data retrieval.

## Prerequisites

- Java 8 or higher
- TestNG
- RestAssured
- Maven

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/dzakwanzaky/weatherAPITest/

2. Open the project in your favorite Java IDE.

3. Install dependencies:

   ```bash
    mvn clean install

## Test Classes

1. GETForecast.class
   This class tests the forecast endpoint to ensure the correctness of weather data retrieved for a specific location (London in this case). It includes assertions for status code, JSON schema, and specific weather conditions.


2. GETPollution.class
   This class tests the air pollution endpoint to verify the air quality index (AQI) for a given location (London). It includes assertions for status code, JSON schema, and checks if the air quality is good.


3. GETWeather.class
   This class tests the current weather endpoint to ensure the accuracy of weather data for a specific location (London). It includes assertions for status code, JSON schema, and checks if the weather conditions match the expected values.

## Running the Tests
Execute the following Maven command to run all tests:

   ```bash
    mvn test
