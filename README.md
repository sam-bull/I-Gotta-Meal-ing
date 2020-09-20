# I Gotta Meal-ing
### 🎶 ooh-ooh 🎶
## Tonight's Gonna Be A Good Night

On average, we spend 54%<sup>1</sup> of our lives looking forward to our next meal. But for many of 
us, the burden of what to cook next is heavy, and we resort to the same 3 meals we have been making 
since we first started cooking for ourselves. That's where this app comes in. It has hundreds of 
recipes for every meal you could ever imagine, from all over the internet - just pick a category, 
browse the meals available, and find the perfect recipe for tonight! Not only that, but this app 
also doubles the number of cloves of garlic in every meal that has them<sup>2</sup>, so you don't have to, 
because everybody knows that the people who write recipes are cowards and never put enough garlic 
in.

So what are you waiting for? Find yourself a recipe, and make tonight A Good Good Night!

## App Structure

This app provides a UI for [TheMealDB API](https://themealdb.com/api.php). The launch activity 
fetches a list of meal categories and displays them in a grid. Tapping on one category launches a 
new activity with a list of meal names, and tapping on a name launches a third activity with the 
details of the meal's recipe; photo, title, ingredients and instructions. When a new activity is 
loaded in, the contents of lists are animated as they are added to the page.

If at any point during a user's journey through the app their internet connection is cut off, the 
app will still show cached versions of any previously loaded data. If the internet connection is 
restored, the lists can be refreshed by swiping down from the top.

The UI adjusts itself for multiple screen sizes; larger screens see more columns in the list views. 
This includes larger phones, tablets and switching from portrait to landscape mode.

The app uses MVVM architecture, Retrofit to make the API calls and Picasso to load in the photos. 
Dependency injection is handled by Dagger2 and data binding uses Kotlin synthetic views. The 
categories and meals are displayed in RecyclerViews. There are unit tests for the ViewModels' logic 
around loading in the API data when the view is loaded.

This app supports Android v7.0 Nougat (API level 24) and above.

## Future Development
There are several ways the app could be improved, and infinite ways it could be taken further:
 * More secure storage of the API key
 * Due to time restrictions, the CachedMealService does not have sufficient tests and some of its 
 functionality is tested by the various ViewModel tests.
 * The cache only stores data that was retrieved since the app was last launched; if the app is 
 killed this data is lost. A proper caching solution should be improved to hold onto data for a 
 fixed time.
 * The API has much more data available; the app could make use of this and display more 
 information, such as a description of each category, or have a button that links to the YouTube 
 video of the meal recipe.
 * The API has other endpoints; the app could implement a search bar to search for recipes by name, 
 or by area.

---

<sup>1</sup> This figure is pure speculation and not backed up by scientific research.

<sup>2</sup> Those who work in a shared office space might want to consider halving it again if 
planning to bring leftovers in to work.
