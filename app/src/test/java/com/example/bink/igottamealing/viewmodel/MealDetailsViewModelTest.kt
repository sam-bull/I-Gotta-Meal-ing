package com.example.bink.igottamealing.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.model.*
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.*

import org.junit.Assert.*
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailsViewModelTest {

    companion object {
        private const val API_KEY = "1"
        private const val MEAL_ID = "mealId"
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MealDetailsViewModel

    @Mock
    lateinit var mealsService: MealsService

    @Mock
    lateinit var mockCall: Call<MealDetailsList>

    @Mock
    lateinit var imageObserver: Observer<String>

    @Mock
    lateinit var titleObserver: Observer<String>

    @Captor
    lateinit var captor: ArgumentCaptor<Callback<MealDetailsList>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MealDetailsViewModel(mealsService)
        viewModel.mealId = MEAL_ID

        viewModel.image.observeForever(imageObserver)
        viewModel.title.observeForever(titleObserver)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(imageObserver)
        verifyNoMoreInteractions(titleObserver)
    }

    @Test
    fun `when the view is created and the meal details request is successful, the UI is updated with the meal details`() {
        // Given
        val meal = MealDetails(
            "idMeal",
            "strMeal",
            "strDrinkAlternate",
            "strCategory",
            "strArea",
            "Put teabag in mug\nBoil the kettle\nPour just-boiled water into mug\nSqueeze teabag and dispose\nAdd milk",
            "strMealThumb",
            "strTags",
            "strYoutube",
            "strIngredient1",
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
            "strMeasure1",
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
            null
        )
        val meals = listOf(meal)
        whenever(mealsService.getMealDetails(API_KEY, MEAL_ID)).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onResponse(
                mockCall,
                Response.success(MealDetailsList(meals))
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        verify(imageObserver, Mockito.times(1)).onChanged("strMealThumb")
        verify(titleObserver, Mockito.times(1)).onChanged("strMeal")
        assertEquals(1, viewModel.ingredients.size)
        assertEquals(Ingredient("strIngredient1", "strMeasure1"), viewModel.ingredients.first())
        assertEquals(5, viewModel.instructions.size)
        assertEquals("Put teabag in mug", viewModel.instructions.first())
    }


    @Test
    fun `when the view is created and the meal details request returns an empty list, the UI is updated with an error`() {
        // Given
        val meals = listOf<MealDetails>()
        whenever(mealsService.getMealDetails(
            API_KEY,
            MEAL_ID
        )).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onResponse(
                mockCall,
                Response.success(MealDetailsList(meals))
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        verify(titleObserver, Mockito.times(1)).onChanged("error")
        }

    @Test
    fun `when the view is created and the meal details request responds with an error, the UI is updated with an error message`() {
        // Given
        whenever(mealsService.getMealDetails(
            API_KEY,
            MEAL_ID
        )).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onResponse(
                mockCall,
                Response.error(400, ResponseBody.create(MediaType.get("application/json"), "error"))
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        verify(titleObserver, Mockito.times(1)).onChanged("error")
    }

    @Test
    fun `when the view is created and the meal details request fails, the UI is updated with an error message`() {
        // Given
        whenever(mealsService.getMealDetails(
            API_KEY,
            MEAL_ID
        )).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onFailure(
                mockCall,
                Throwable("error")
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        verify(titleObserver, Mockito.times(1)).onChanged("error")
    }
}