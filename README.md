# CountriesList Android App

## Overview
CountriesList is an Android application showcasing a list of countries. It's built using the MVVM architectural pattern and modern Android development practices.

## Project Structure

### Kotlin Code (`java/com/example/countrieslist`)
Organized into model, UI, and ViewModel, the project demonstrates a clean and modular architecture.

#### Model (`model`)
Handles data and network interactions.
- `Country.kt`: Defines the Country data model.
- `IRepository.kt`: Interface for data repository.
- `RepositoryImpl.kt`: Implementation of the repository interface.
- `UIState.kt`: Manages UI states for the application.
- Network Layer (`network`): Handles API interactions.
  - `CountryService.kt`: Service interface for country API calls.
  - `Network.kt`: Sets up the network configuration.
  - `NetworkConstant.kt`: Defines constants used in network configuration.

#### UI (`ui`)
Contains UI-related classes and activities.
- `CountriesAdapter.kt`: Adapter for rendering the countries list.
- `CountryFragment.kt`: Fragment for displaying country details.
- `MainActivity.kt`: The main activity of the app.

#### ViewModel (`viewmodel`)
- `CountriesViewModel.kt`: ViewModel managing the countries data.

### Resources (`res`)

#### Layouts (`layout`)
XML files defining the UI structure of the app.
- `activity_main.xml`: Main activity layout.
- `country_fragment_layout.xml`: Layout for the country detail fragment.
- `country_item.xml`: Layout for each item in the countries list.

## Build and Run
1. Open the project in Android Studio.
2. Sync with Gradle files.
3. Run on an emulator or a physical device.

## Dependencies
Important dependencies used in the project:
- Kotlin Standard Library: `org.jetbrains.kotlin:kotlin-stdlib-jdk8`
- AndroidX Core KTX: `androidx.core:core-ktx:1.8.0`
- AndroidX Lifecycle Runtime KTX: `androidx.lifecycle:lifecycle-runtime-ktx:2.3.1`
- AndroidX Activity Compose: `androidx.activity:activity-compose:1.5.1`
- AndroidX Compose UI: `androidx.compose.ui:ui`
- AndroidX RecyclerView: `androidx.recyclerview:recyclerview:1.2.1`
- Gson: `com.google.code.gson:gson:2.8.8`
- Retrofit: `com.squareup.retrofit2:retrofit:2.9.0`
- Moshi Converter: `com.squareup.retrofit2:converter-moshi:2.9.0`

