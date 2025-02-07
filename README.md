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
  GET /cities/getAllDistricts?countryId=60e4482c7cb7d4bc4849c4d5
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

## Getting Started

1. Clone the repository:
   ```sh
   git clone https://github.com/KarimRaouf/Bosta-Task.git
   ```
2. Open the project in **Android Studio**.
3. Run the app on an emulator or physical device.
