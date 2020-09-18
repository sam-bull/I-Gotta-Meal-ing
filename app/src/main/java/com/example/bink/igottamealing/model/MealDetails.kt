package com.example.bink.igottamealing.model

data class MealDetailsList(
    val meals: List<MealDetails>
)

data class MealDetails(
    val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String?,
    val strYoutube: String,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,
    val strIngredient16: String,
    val strIngredient17: String,
    val strIngredient18: String,
    val strIngredient19: String,
    val strIngredient20: String,
    val strMeasure1: String,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
    val strMeasure16: String,
    val strMeasure17: String,
    val strMeasure18: String,
    val strMeasure19: String,
    val strMeasure20: String,
    val strSource: String,
    val dateModified: String?
) {

    fun getIngredients() = listOf(
        Ingredient(strIngredient1, strMeasure1),
        Ingredient(strIngredient2, strMeasure2),
        Ingredient(strIngredient3, strMeasure3),
        Ingredient(strIngredient4, strMeasure4),
        Ingredient(strIngredient5, strMeasure5),
        Ingredient(strIngredient6, strMeasure6),
        Ingredient(strIngredient7, strMeasure7),
        Ingredient(strIngredient8, strMeasure8),
        Ingredient(strIngredient9, strMeasure9),
        Ingredient(strIngredient10, strMeasure10),
        Ingredient(strIngredient11, strMeasure11),
        Ingredient(strIngredient12, strMeasure12),
        Ingredient(strIngredient13, strMeasure13),
        Ingredient(strIngredient14, strMeasure14),
        Ingredient(strIngredient15, strMeasure15),
        Ingredient(strIngredient16, strMeasure16),
        Ingredient(strIngredient17, strMeasure17),
        Ingredient(strIngredient18, strMeasure18),
        Ingredient(strIngredient19, strMeasure19),
        Ingredient(strIngredient20, strMeasure20)
    ).filter { ingredient -> ingredient.name.isNotEmpty() }
}

data class Ingredient(val name: String, val measure: String) {
    override fun toString() = "$name - $measure"
}