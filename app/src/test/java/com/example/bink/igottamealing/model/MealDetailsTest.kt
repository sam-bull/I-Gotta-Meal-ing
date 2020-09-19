package com.example.bink.igottamealing.model

import org.junit.Test

import org.junit.Assert.*

class MealDetailsTest {

    // The implementation of MealDetails and these tests assume the API always returns the same
    // number of measures as ingredients

    lateinit var mealDetails: MealDetails
    lateinit var ingredients: List<Ingredient>
    lateinit var ingredientsString: List<String>

    @Test
    fun `when all ingredients fields are populated, the ingredients list and strings are correctly created`() {
         mealDetails = MealDetails(
             "idMeal",
                     "strMeal",
                     "strDrinkAlternate",
                     "strCategory",
                     "strArea",
                     "strInstructions",
                     "strMealThumb",
                     "strTags",
                     "strYoutube",
                     "strIngredient1",
                     "strIngredient2",
                     "strIngredient3",
                     "strIngredient4",
                     "strIngredient5",
                     "strIngredient6",
                     "strIngredient7",
                     "strIngredient8",
                     "strIngredient9",
                     "strIngredient10",
                     "strIngredient11",
                     "strIngredient12",
                     "strIngredient13",
                     "strIngredient14",
                     "strIngredient15",
                     "strIngredient16",
                     "strIngredient17",
                     "strIngredient18",
                     "strIngredient19",
                     "strIngredient20",
                     "strMeasure1",
                     "strMeasure2",
                     "strMeasure3",
                     "strMeasure4",
                     "strMeasure5",
                     "strMeasure6",
                     "strMeasure7",
                     "strMeasure8",
                     "strMeasure9",
                     "strMeasure10",
                     "strMeasure11",
                     "strMeasure12",
                     "strMeasure13",
                     "strMeasure14",
                     "strMeasure15",
                     "strMeasure16",
                     "strMeasure17",
                     "strMeasure18",
                     "strMeasure19",
                     "strMeasure20",
                     "strSource",
                     "dateModified"
         )
        ingredients = listOf(
            Ingredient("strIngredient1", "strMeasure1"),
            Ingredient("strIngredient2", "strMeasure2"),
            Ingredient("strIngredient3", "strMeasure3"),
            Ingredient("strIngredient4", "strMeasure4"),
            Ingredient("strIngredient5", "strMeasure5"),
            Ingredient("strIngredient6", "strMeasure6"),
            Ingredient("strIngredient7", "strMeasure7"),
            Ingredient("strIngredient8", "strMeasure8"),
            Ingredient("strIngredient9", "strMeasure9"),
            Ingredient("strIngredient10", "strMeasure10"),
            Ingredient("strIngredient11", "strMeasure11"),
            Ingredient("strIngredient12", "strMeasure12"),
            Ingredient("strIngredient13", "strMeasure13"),
            Ingredient("strIngredient14", "strMeasure14"),
            Ingredient("strIngredient15", "strMeasure15"),
            Ingredient("strIngredient16", "strMeasure16"),
            Ingredient("strIngredient17", "strMeasure17"),
            Ingredient("strIngredient18", "strMeasure18"),
            Ingredient("strIngredient19", "strMeasure19"),
            Ingredient("strIngredient20", "strMeasure20")
        )
        ingredientsString = listOf(
            "strIngredient1 - strMeasure1",
            "strIngredient2 - strMeasure2",
            "strIngredient3 - strMeasure3",
            "strIngredient4 - strMeasure4",
            "strIngredient5 - strMeasure5",
            "strIngredient6 - strMeasure6",
            "strIngredient7 - strMeasure7",
            "strIngredient8 - strMeasure8",
            "strIngredient9 - strMeasure9",
            "strIngredient10 - strMeasure10",
            "strIngredient11 - strMeasure11",
            "strIngredient12 - strMeasure12",
            "strIngredient13 - strMeasure13",
            "strIngredient14 - strMeasure14",
            "strIngredient15 - strMeasure15",
            "strIngredient16 - strMeasure16",
            "strIngredient17 - strMeasure17",
            "strIngredient18 - strMeasure18",
            "strIngredient19 - strMeasure19",
            "strIngredient20 - strMeasure20"
        )
        assertions()
    }

    @Test
    fun `when some ingredients fields are populated, the ingredients list and strings are correctly created`() {
        mealDetails = MealDetails(
            "idMeal",
            "strMeal",
            "strDrinkAlternate",
            "strCategory",
            "strArea",
            "strInstructions",
            "strMealThumb",
            "strTags",
            "strYoutube",
            "strIngredient1",
            "strIngredient2",
            "strIngredient3",
            "strIngredient4",
            "strIngredient5",
            "strIngredient6",
            "strIngredient7",
            "strIngredient8",
            "strIngredient9",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "strMeasure1",
            "strMeasure2",
            "strMeasure3",
            "strMeasure4",
            "strMeasure5",
            "strMeasure6",
            "strMeasure7",
            "strMeasure8",
            "strMeasure9",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "strSource",
            "dateModified"
        )
        ingredients = listOf(
            Ingredient("strIngredient1", "strMeasure1"),
            Ingredient("strIngredient2", "strMeasure2"),
            Ingredient("strIngredient3", "strMeasure3"),
            Ingredient("strIngredient4", "strMeasure4"),
            Ingredient("strIngredient5", "strMeasure5"),
            Ingredient("strIngredient6", "strMeasure6"),
            Ingredient("strIngredient7", "strMeasure7"),
            Ingredient("strIngredient8", "strMeasure8"),
            Ingredient("strIngredient9", "strMeasure9")
        )
        ingredientsString = listOf(
            "strIngredient1 - strMeasure1",
            "strIngredient2 - strMeasure2",
            "strIngredient3 - strMeasure3",
            "strIngredient4 - strMeasure4",
            "strIngredient5 - strMeasure5",
            "strIngredient6 - strMeasure6",
            "strIngredient7 - strMeasure7",
            "strIngredient8 - strMeasure8",
            "strIngredient9 - strMeasure9"
        )
        assertions()
    }

    @Test
    fun `when none of the ingredients fields are populated, the ingredients list and strings are correctly created`() {
        mealDetails = MealDetails(
            "idMeal",
            "strMeal",
            "strDrinkAlternate",
            "strCategory",
            "strArea",
            "strInstructions",
            "strMealThumb",
            "strTags",
            "strYoutube",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "strSource",
            "dateModified"
        )
        ingredients = listOf()
        ingredientsString = listOf()
        assertions()
    }

    private fun assertions() {
        assertEquals(ingredients, mealDetails.getIngredients())
        assertEquals(ingredientsString.size, mealDetails.getIngredients().size)
        mealDetails.getIngredients().forEachIndexed { index, ingredient ->
            assertEquals(ingredientsString[index], ingredient.toString())
        }
    }
}