# ExerciseProgress
![exerciseprogress](https://github.com/user-attachments/assets/a5197d54-81a4-474d-b6b9-424a8edf4668)

## Built With

- **[Kotlin](https://kotlinlang.org/)** – First class and official programming language for Android development
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)** – Modern toolkit for building native UI
- **[Coroutines](https://developer.android.com/kotlin/coroutines)** – Asynchronous programming and concurrency
- **[Flow](https://developer.android.com/kotlin/flow)** – Reactive streams for handling asynchronous data
- **[Jetpack Libraries](https://developer.android.com/jetpack)**
  - [Navigation Compose](https://developer.android.com/guide/navigation) - Type-safe navigation for Compose
  - [Material 3](https://developer.android.com/jetpack/androidx/releases/compose-material3) - Material Design 3 components
- **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)** – Kotlin's native serialization library.
- **[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)** – Dependency injection framework built on top of Dagger  
- **[Coil](https://coil-kt.github.io/coil/)** – Lightweight, Compose-first image loading library for Android     
- **[JUnit](https://junit.org/junit4/)** - Unit testing framework
- **[MockK](https://mockk.io/)** - Mocking library for Kotlin

## Architecture Overview

```
📦 ExerciseProgress
├── 🧩 app          # Presentation layer - UI, navigation, and interaction
│   ├── di          # Dependency injection (Hilt modules)
│   ├── feature     # Jetpack Compose screens, UI logic, and ViewModels
│   ├── navigation  # Navigation graph and route definitions
│   └── ui          # Material 3 styling, theming, and components
│
├── 🧩 domain       # Business logic and use cases
│   ├── usecase     # UseCases coordinating business rules
│   └── repo        # Repositories
│
├── 🧩 data         # Data sources
│   ├── datasource   # Local data source
│   └── models       # DTOs
│
├── 🧩 core         # Shared utilities and common functionality
│   └── util        # Date formatting, extension functions, etc.
```

# Comments
I took the initiative of modularizing the project for better separation of concerns and maintainability. If the project included network calls, I would expand the data module with dedicated service and database packages. For a larger app, I would introduce a core-ui module to host reusable UI components along with shared styling, typography, and theming. and add a feature module to organize distinct features and their corresponding screens.

# Android Engineering Project - Exercise Progress

In this project, we've set up a realistic scenario of the type of feature development we'd do here at Future. We'd like you to do the brainstorming, planning, building, and presenting. Basically, we're attempting to create a compressed microcosm of working at Future!

Please take 2 hours to work on the project (excluding any time reading this document + formulating a plan). Please feel free to reach out to us to ask questions and engage us for assistance where needed before and during the project.

Afterwards, you’ll sit with the team and lead a 30-minute discussion around the progress you made, how you thought about the problem & solution, and what you might do if given more time.

We are much more interested in how you were thinking about the problem and solution than the polish and completeness of your work – just make as much progress as you can given the time you have.

## Problem Statement

Future coaches often look at athletic performance (such as weight lifted) over time for a client as an indication of progress towards a specific goal.

We’d like you to construct a simple app that shows this information about a single client based on their recorded workout data. This app should have:

* A way to select a specific exercise completed by the client.
* A way to see the historical performance data.

It’s up to you how to organize and present this information as long as it meets these requirements.

## Data Model Architecture

This is a Entity Relationship Diagram of the data models we use to store workout data. We only show some of more important fields for brevity, but feel free to use any other fields you need.

```mermaiderDiagram
    Workout ||--o{ WorkoutSection : "has sections"
    Workout ||--o{ WorkoutSummary : "has summaries"
    WorkoutSection ||--o{ ExerciseSet : "contains exerciseSets"
    ExerciseSet ||--o| Exercise : "references"
    WorkoutSummary ||--o{ ExerciseSetSummary : "has setSummaries"
    ExerciseSetSummary ||--o| ExerciseSet : "references"

    Workout {
        string id PK
        string name
        string description
        string scheduledAt
        WorkoutSection[] sections
        WorkoutSummary[] summaries
    }

    WorkoutSection {
        string id PK
        string name
        string workoutID FK
        ExerciseSet[] exerciseSets
    }

    ExerciseSet {
        string id PK
        ExerciseSetType type
        int reps
        int duration
        float weight
        double distance
        Exercise exercise
    }

    Exercise {
        string id PK
        string name
        string description
        string side
        string muscleGroups
        string equipmentRequired
        double estimatedRepDuration
        boolean isAlternating
    }

    WorkoutSummary {
        string workoutID FK
        string workoutSummaryID
        string completedAt
        WorkoutCompletionState completionState
        int activeEnergyBurned
        int averageHeartRate
        float difficulty
        ExerciseSetSummary[] setSummaries
    }

    ExerciseSetSummary {
        string id PK
        string exerciseSetID FK
        string workoutSummaryID FK
        string completedAt
        int reps
        int duration
        float weight
        double distance
    }
```

## Project Details

We’ve provided a basic Android project skeleton with a data set with workout data for the client, and the corresponding models and loading functions in Kotlin so you can get started quickly.

The final output should be an app that runs in an Android emulator, written in Kotlin. You’re free to use any other tools or libraries that you are comfortable with.

After the project period, we’d like you to present to the team about what you built, so save a few minutes at the end to collect your thoughts. We’ll also want to see the work that you did along the way!

## Agentic Coding

We would prefer that you **do not** use AI coding agents like Claude Code for this project (basic AI-assisted tab completions from Android Studio / Gemini are OK). However, if you choose to use one, please follow these guidelines:

* Include all the prompts you used to generate code
* Include all the configuration / rules you used to guide the AI Coding Agents (.CLAUDE.md, .cursor/rules, etc.)
* Include a short paragraph of your workflow and tools used
* We expect submissions completed with aid of AI coding agents to have more polish and go above and beyond the requirements (more features, better UI, etc.)
* **DO NOT** use the AI coding agents to write the entire app. We want to see your thought process and problem-solving skills
