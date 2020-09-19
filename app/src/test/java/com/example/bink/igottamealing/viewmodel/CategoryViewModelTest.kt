package com.example.bink.igottamealing.viewmodel

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.model.Meal
import com.example.bink.igottamealing.model.Meals
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.*
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModelTest {

    companion object {
        private const val API_KEY = "1"
        private const val CATEGORY = "category"
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CategoryViewModel

    @Mock
    lateinit var resources: Resources

    @Mock
    lateinit var mealsService: MealsService

    @Mock
    lateinit var mockCall: Call<Meals>

    @Mock
    lateinit var mealsLoadedObserver: Observer<Boolean>

    @Mock
    lateinit var errorTextObserver: Observer<String>

    @Captor
    lateinit var captor: ArgumentCaptor<Callback<Meals>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = CategoryViewModel(resources, mealsService)
        viewModel.category = CATEGORY

        whenever(resources.getString(R.string.error_message, "error")).thenReturn("error")

        viewModel.mealsLoaded.observeForever(mealsLoadedObserver)
        viewModel.errorText.observeForever(errorTextObserver)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mealsLoadedObserver)
        verifyNoMoreInteractions(errorTextObserver)
    }

    @Test
    fun `when the view is created and the meals request is successful, the UI is updated with the meals`() {
        // Given
        val meal = Meal("id", "url", "name")
        val meals = listOf(meal)
        whenever(mealsService.getMealsForCategory(API_KEY, CATEGORY)).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onResponse(
                mockCall,
                Response.success(Meals(meals))
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        Assert.assertTrue(viewModel.meals.size == 1)
        Assert.assertEquals(meal, viewModel.meals[0])
        verify(mealsLoadedObserver, Mockito.times(1)).onChanged(true)
    }

    @Test
    fun `when the view is created and the meals request returns an empty list, the UI is updated with the meals`() {
        // Given
        val meals = listOf<Meal>()
        whenever(mealsService.getMealsForCategory(API_KEY, CATEGORY)).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onResponse(
                mockCall,
                Response.success(Meals(meals))
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        Assert.assertTrue(viewModel.meals.size == 0)
        verify(mealsLoadedObserver, Mockito.times(1)).onChanged(true)
    }

    @Test
    fun `when the view is created and the meals request responds with an error, the UI is updated with an error message`() {
        // Given
        whenever(mealsService.getMealsForCategory(API_KEY, CATEGORY)).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onResponse(
                mockCall,
                Response.error(400, ResponseBody.create(MediaType.get("application/json"), "error"))
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        Assert.assertTrue(viewModel.meals.size == 0)
        verify(mealsLoadedObserver, Mockito.times(1)).onChanged(false)
        verify(errorTextObserver, Mockito.times(1)).onChanged("error")
    }

    @Test
    fun `when the view is created and the meals request fails, the UI is updated with an error message`() {
        // Given
        whenever(mealsService.getMealsForCategory(API_KEY, CATEGORY)).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onFailure(
                mockCall,
                Throwable("error")
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        Assert.assertTrue(viewModel.meals.size == 0)
        verify(mealsLoadedObserver, Mockito.times(1)).onChanged(false)
        verify(errorTextObserver, Mockito.times(1)).onChanged("error")

    }
}