# YapeTestChallenge

Requested by Oswaldo Miguel Ortega Montero from Yape Recruitment Team and developed by Joel Colmenares

## Languages, libraries and tools used

* __[Kotlin](https://developer.android.com/kotlin)__ - As main language.
* __[Room](https://developer.android.com/topic/libraries/architecture/room)__ - For persisting the requested data from the service.
* __[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)__ - for async programming.
* __[ViewBinding](https://developer.android.com/topic/libraries/view-binding)__ - was implemented to take advantages of the safe vinculation of the view and its binding.
* __[Koin](https://github.com/InsertKoinIO/koin)__ - As Dependency Injection Framework.
* __[Android Material Design](https://material.io/components/)__ - For basic styling.
* __[Gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)__ - To deserealize the data provided by the services.
* __[Retrofit2](https://square.github.io/retrofit/)__ - A network framework that facilitates the way of how the requests are made.
* __[Android Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)__ - To delegate the navigation of the application.
* __[JUnit4](https://developer.android.com/training/testing/instrumented-tests/androidx-test-libraries/rules)__ - For testing
* __[KotlinTest](https://developer.android.com/kotlin/coroutines/test)__ - For testing.
* __[Glide](https://github.com/bumptech/glidet)__ - For caching images in the project. 
* __[GoogleMapsAPI](https://developers.google.com/maps/documentation/android-sdk)__ - For maps implementations.

## Architecture 

The architecture used for this project is the Clean Architecture in order to separate the concerns of each layer 
and keeping the communication between them through abstractions of it. As you can check/see that
some SOLID principles where applied for the better understanding of each class/component such as 
__Single Responsibility__ and __Dependency Inversion__ in general.

## Project Structure
```
../data
    ../local            // where the local data persistance implementation is loated
    ../repositories     // The implementation of repositories are located here
    ../cloud            // Everything related with the requests to the API service
    ../entities         // The entities that belongs to the data-layer are defined here. Such as: Entities and Responses
    ../mappers          // Mappers that prepares the data to be retrieved to upper layers
../domain               // The abstraction of use cases are defined here
    ../usecases         // The definition of use cases are defined here
    ../repositories     // Here are the abstractions of the data-layer 
    ../entities         // The entities that belongs to the domain-layer are defined here.
../ui
    ../fragments        // Fragments are located here
    ../models           // All the models related to the feature will be defined here
    ../viewmodels       // ViewModels are defined here
        ../viewstates   // viewStates represents a state of the view to organize the view logic
    ../mappers          // Mappers that prepares the upcoming data from lower layers to be binded in the view
    ../adapters         // Adapters 
../di                   // The Dependency Injection Module is defined here
../utils                // Here are some util function and classes that will improve the implementation of the code
```

## Running and Building the application

You can run the app on a real device or an emulator.

* __[Run on a real device](https://developer.android.com/training/basics/firstapp/running-app#RealDevice)__
* __[Run on an emulator](https://developer.android.com/training/basics/firstapp/running-app#Emulator)__


# Built With

* __[Android Studio](https://developer.android.com/studio/index.html)__ - The Official IDE for Android
* __[Kotlin](https://developer.android.com/kotlin)__ - Language used to build the application
* __[Gradle](https://gradle.org)__ - Build tool for Android Studio

Thanks for the opportunity! 
I must say that I did enjoy developing this challenge for this opportunity.

Want to keep checking another projects? 
You can either check [this](https://github.com/jkhin/zemoga-mobile-test) repo.

Also, Let’s become friends on [Linkedin](https://www.linkedin.com/in/joiky/)
