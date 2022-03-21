# PhotoLibrary


The project uses the approach of MVVM + Clean Architecture 

 Domain Layer --  The domain encapsulates business logic. The components of the domain are:

Entities: Simple classes that represent the objects in which the business is based.
Repositories: Interfaces used by the use cases. Implemented in the data layer.
Use cases:  They act as a single action, like getting data from a database or posting to a service. They use the repositories to resolve the action. 


Data

The data layer is the implementation of all the repositories declared by the domain layer. 


Presentation

Presentation layer contains every component involved in showing information to the user having to deal with views and view models. These contain Fragments and activities which shows information to the user


Libraries used for this project


Glide for image loading
Dependency Injection -- Hilt
Networking -- Retrofit
ViewModel -- Store UI-related data that isn't destroyed on app rotations.
Live Data -- Build data objects that notify views when the underlying database changes.
Couroutines -- for managing background threads with simplified code and reducing needs for callbacks
