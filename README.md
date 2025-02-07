# Task - Cities and Districts App

## Overview

In this simple task, we are evaluating your **coding style** and **design patterns**. The app consists of a single screen that lists all cities and districts by fetching data from the provided API endpoint.

## Technologies and Tools

- **Kotlin**
- **MVVM Architecture** (Feel free to use your own implementation/vision)
- **RecyclerView**
- **Retrofit 2**
- **Dagger 2**
- **Coroutines**

### API Endpoints

- **Base URL:** `https://stg-app.bosta.co/api/v2`
- **Cities and Districts:**
  ```
  GET /cities/getAllDistricts?countryId= id
  ```
- Make sure to **check the API response using Postman** before integrating.

## Architecture

```
├── core
│   ├── di (Dependency Injection)
│   ├── networking (API handling)
│   ├── utilities (Helper functions, extensions)
│
├── data
│   ├── model (Data Models)
│   ├── repo (Repository Implementations)
│   ├── services (API Services)
│
├── domain
│   ├── repo (Repository Interfaces)
│
├── presentation
│   ├── screens (UI & ViewModels)
```

## Screenshot
![Screenshot_20230708_171046](https://github.com/user-attachments/assets/9369359c-c8df-4c3a-ac91-105558c59298)



## Videorecording
https://github.com/user-attachments/assets/6e11cbe4-2a94-4cba-a7c1-94db38d4279c


## Getting Started

1. Clone the repository:
   ```sh
   git clone https://github.com/KarimRaouf/Bosta-Task.git
   ```
2. Open the project in **Android Studio**.
3. Run the app on an emulator or physical device.
