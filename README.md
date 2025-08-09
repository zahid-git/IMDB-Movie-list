# 🎬 IMDB Movie List

An Android application built with **Kotlin** using the **MVVM** architecture.  
It fetches movie data from a given URL, stores it in a local **Room** database, and displays it with smooth performance — even offline.


## 📌 Features

- **Fetch movie data** (currently from a fake/mock API).
- **Store data locally** in a Room database for offline access.
- **Display movie list** with images and details.
- **MVVM architecture** for clean, maintainable code.
- **Jetpack components** for modern Android development.

## 🛠️ Tech Stack

- **Language:** Kotlin  
- **Architecture:** MVVM  
- **Database:** Room  
- **Networking:** Retrofit / OkHttp (if applicable)  
- **UI:** Jetpack Compose / XML (depending on UI implementation)  
- **Coroutines** for asynchronous tasks


## 🚀 Getting Started

### 1️⃣ Prerequisites
- Android Studio **Giraffe** or newer  
- JDK 17+  
- Gradle 8+  
- Android device or emulator with **minSdk 24** (adjust if different)

### 2️⃣ Clone the Repository
git clone https://github.com/zahid-git/IMDB-Movie-list.git

### 3️⃣ Open in Android Studio
Open the project in Android Studio.

Let Gradle sync and download dependencies.

### 4️⃣ Run the App
Select a device/emulator.

Press Run ▶.

⚙️ How It Works
- Fetch Data → App requests JSON movie data from the given URL.
- Save to Room → The data is cached locally for offline usage.
- Display → Movies are loaded from Room and displayed in a list/grid.

📷 Sample

https://www.youtube.com/shorts/swkecIDb_gw

