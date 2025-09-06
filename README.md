# GitHub Showcase - Repository Discovery App

> ℹ️ This project was created in 2021 for recruitment purposes and has not been updated since then. The technologies, tools, and other components used have significantly changed since that time - this project does not represent current standards and best practices.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![MVVM](https://img.shields.io/badge/MVVM-FF6B6B?style=for-the-badge&logo=android&logoColor=white)
![GitHub API](https://img.shields.io/badge/GitHub%20API-181717?style=for-the-badge&logo=github&logoColor=white)

## Table of Contents

- [Project Description](#project-description)
- [Features](#features)
  - [Repository List Screen](#-repository-list-screen)
  - [Repository Details Screen](#-repository-details-screen)
  - [Splash Screen](#-splash-screen)
  - [Search Functionality](#-search-functionality)
- [Technical Architecture](#technical-architecture)
  - [Clean Architecture](#️-clean-architecture)
  - [Technologies](#️-technologies)
  - [API Integration](#-api-integration)
  - [Dependency Injection](#-dependency-injection)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Installation and Setup](#installation-and-setup)
- [Testing](#testing)
- [Build Variants](#build-variants)

## Project Description

**GitHub Showcase** is a native Android application built with Kotlin that enables users to discover and explore GitHub repositories. The app provides a clean interface for searching repositories, viewing detailed information, and exploring repository owners and their followers.

## Features

### 📋 Repository List Screen
- Search GitHub repositories with real-time query updates
- Display repository information including name, description, language, and statistics
- Pull-to-refresh functionality for updated data
- Smooth navigation to repository details

### 📱 Repository Details Screen
- Comprehensive repository information display
- Repository statistics (stars, watchers, forks)
- Owner information with avatar and follower count
- Creation and last update dates
- Language and description details

### 🚀 Splash Screen
- Animated loading screen with rotating progress indicator
- Full-screen immersive experience
- Automatic navigation to main content

### 🔍 Search Functionality
- Real-time search as you type
- GitHub API integration for repository discovery
- Error handling and loading states

## Technical Architecture

### 🏗️ Clean Architecture
- **Presentation Layer**: Fragments + ViewModels with MVVM pattern
- **Domain Layer**: Use Cases + Repository interfaces
- **Data Layer**: Repository implementations + GitHub API integration

### 🛠️ Technologies

| Category | Technology |
|----------|------------|
| **UI** | ![Android Views](https://img.shields.io/badge/Android%20Views-3DDC84?style=flat&logo=android&logoColor=white) ![Material Design](https://img.shields.io/badge/Material%20Design-757575?style=flat&logo=materialdesign&logoColor=white) |
| **Architecture** | ![MVVM](https://img.shields.io/badge/MVVM-FF6B6B?style=flat&logo=android&logoColor=white) ![Clean Architecture](https://img.shields.io/badge/Clean%20Architecture-4ECDC4?style=flat&logo=android&logoColor=white) |
| **DI** | ![Koin](https://img.shields.io/badge/Koin-FF6B35?style=flat&logo=android&logoColor=white) |
| **Navigation** | ![Navigation Component](https://img.shields.io/badge/Navigation%20Component-4285F4?style=flat&logo=android&logoColor=white) |
| **Networking** | ![Fuel](https://img.shields.io/badge/Fuel-FF6B35?style=flat&logo=android&logoColor=white) ![Kotlinx Serialization](https://img.shields.io/badge/Kotlinx%20Serialization-7F52FF?style=flat&logo=kotlin&logoColor=white) |
| **Image Loading** | ![Glide](https://img.shields.io/badge/Glide-FF6B35?style=flat&logo=android&logoColor=white) |
| **Async** | ![Kotlin Coroutines](https://img.shields.io/badge/Kotlin%20Coroutines-7F52FF?style=flat&logo=kotlin&logoColor=white) ![Flow](https://img.shields.io/badge/Flow-7F52FF?style=flat&logo=kotlin&logoColor=white) |
| **Testing** | ![Kotest](https://img.shields.io/badge/Kotest-7F52FF?style=flat&logo=kotlin&logoColor=white) ![Mockito](https://img.shields.io/badge/Mockito-FF6B35?style=flat&logo=android&logoColor=white) |

### 📊 API Integration
- **GitHub API** integration for repository data
- Repository search endpoint
- Repository details endpoint
- User information and followers data
- Image loading from GitHub CDN

### 💉 Dependency Injection
- **Koin** for lightweight dependency injection
- Modular architecture with separate modules
- ViewModel injection with Koin AndroidX extensions

## Project Structure

```
app/src/main/java/xyz/kewiany/showcase/
├── api/              # API services and interfaces
├── details/          # Repository details feature
├── di/               # Dependency Injection modules
├── entity/           # Data models (Repository, User)
├── list/             # Repository list feature
├── splash/           # Splash screen feature
├── utils/            # Utility classes and extensions
├── AppState.kt       # Application state management
├── MainActivity.kt   # Main activity
└── ShowcaseApp.kt    # Application class
```

## Requirements

| Requirement | Version |
|-------------|---------|
| **JDK** | ![JDK](https://img.shields.io/badge/JDK-8-orange?style=flat&logo=openjdk&logoColor=white) (Java 8) |
| **Android Studio** | ![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=flat&logo=androidstudio&logoColor=white) (latest stable) |
| **Min SDK** | ![Min SDK](https://img.shields.io/badge/Min%20SDK-23-green?style=flat&logo=android&logoColor=white) (Android 6.0) |
| **Target SDK** | ![Target SDK](https://img.shields.io/badge/Target%20SDK-30-blue?style=flat&logo=android&logoColor=white) (Android 11) |

## Testing

### 🧪 Unit tests:
```bash
./gradlew :app:testDebugUnitTest
```

![Testing](https://img.shields.io/badge/Testing-Kotest-green?style=flat&logo=kotlin&logoColor=white)
![Mocking](https://img.shields.io/badge/Mocking-Mockito-blue?style=flat&logo=android&logoColor=white)

The project includes comprehensive unit tests using:
- **Kotest** framework for testing
- **Mockito** for mocking dependencies
- **Coroutines testing** utilities
- **SMokK** for additional testing utilities

## Build Variants

The project supports multiple build variants:

### Product Flavors
- **apiMocked**: Uses mocked API responses for testing
- **apiProduction**: Uses real GitHub API endpoints

### Build Types
- **debug**: Development build with debugging enabled
- **qa**: Quality assurance build with minification
- **release**: Production build with full optimization

### Build Configuration
```kotlin
// Example build variant combinations:
// apiMockedDebug - Mocked API with debug features
// apiProductionDebug - Real API with debug features  
// apiProductionQa - Real API with QA optimizations
// apiProductionRelease - Production-ready build
```

---
