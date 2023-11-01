# cocktails

This is a cocktails app which implements TheCocktailDB API. It has 3 different screens:

- Splash screen: Animation that is shown while the data is retrieved from the API.
- Cocktail list: RecyclerView that shows a list of cocktails.
- Cocktail detail: Displays some of the cocktail details such as the image, name, ingredients and instructions.

The architecture is based on Clean concepts and MVVM, with 3 separate layers: data, domain and presentation. It includes a Repository pattern to have a single source of truth, local and remote DataSources, UseCases and unit tests.

The app implements:

- Room Database to store the cocktails and ingredients, so the app can be used offline and the number of requests is lower.
- DI with hilt to provide the API, database, repository and use cases.
- Navigation with SafeArgs.
- Lottie animations.
- Parallax effect in the detail screen.
- Shared animation between 2 fragments.

Install APK -----> https://drive.google.com/file/d/1tj8Jt6QSf5l91OGuKvG-H5ILQ75XncC7/view?usp=sharing
