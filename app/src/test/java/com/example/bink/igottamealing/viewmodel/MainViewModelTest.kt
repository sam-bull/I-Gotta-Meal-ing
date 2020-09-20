package com.example.bink.igottamealing.viewmodel

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.api.CachedMealsService
import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.model.Categories
import com.example.bink.igottamealing.model.Category
import com.nhaarman.mockitokotlin2.*
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import retrofit2.Callback
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import org.junit.After
import org.mockito.ArgumentCaptor
import org.mockito.Captor

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    private lateinit var cachedMealsService: CachedMealsService

    @Mock
    lateinit var resources: Resources

    @Mock
    lateinit var mealsService: MealsService

    @Mock
    lateinit var mockCall: Call<Categories>

    @Mock
    lateinit var categoriesLoadedObserver: Observer<Boolean>

    @Mock
    lateinit var errorTextObserver: Observer<String>

    @Captor
    lateinit var captor: ArgumentCaptor<Callback<Categories>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cachedMealsService = CachedMealsService(mealsService)
        viewModel = MainViewModel(resources, cachedMealsService)

        whenever(resources.getString(R.string.error_message, "error")).thenReturn("error")
        viewModel.categoriesLoaded.observeForever(categoriesLoadedObserver)
        viewModel.errorText.observeForever(errorTextObserver)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(categoriesLoadedObserver)
        verifyNoMoreInteractions(errorTextObserver)
    }

    @Test
    fun `when the view is created and the categories request is successful, the UI is updated with the categories`() {
        // Given
        val category = Category("id", "name", "url", "desc")
        val categories = listOf(category)
        whenever(mealsService.getCategories("1")).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onResponse(
                mockCall,
                Response.success(Categories(categories))
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        assertTrue(viewModel.categories.size == 1)
        assertEquals(category, viewModel.categories[0])
        verify(categoriesLoadedObserver, times(1)).onChanged(true)
    }

    @Test
    fun `when the view is created and the categories request returns an empty list, the UI is updated with the categories`() {
        // Given
        val categories = listOf<Category>()
        whenever(mealsService.getCategories("1")).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onResponse(
                mockCall,
                Response.success(Categories(categories))
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        assertTrue(viewModel.categories.size == 0)
        verify(categoriesLoadedObserver, times(1)).onChanged(true)
    }

    @Test
    fun `when the view is created and the categories request responds with an error, the UI is updated with an error message`() {
        // Given
        whenever(mealsService.getCategories("1")).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onResponse(
                mockCall,
                Response.error(400, ResponseBody.create(MediaType.get("application/json"), "error"))
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        assertTrue(viewModel.categories.size == 0)
        verify(categoriesLoadedObserver, times(1)).onChanged(false)
        verify(errorTextObserver, times(1)).onChanged("error")
    }

    @Test
    fun `when the view is created and the categories request fails, the UI is updated with an error message`() {
        // Given
        whenever(mealsService.getCategories("1")).thenReturn(mockCall)
        whenever(mockCall.enqueue(captor.capture())).then {
            captor.value.onFailure(
                mockCall,
                Throwable("error")
            )
        }

        // When
        viewModel.onViewCreated()

        // Then
        assertTrue(viewModel.categories.size == 0)
        verify(categoriesLoadedObserver, times(1)).onChanged(false)
        verify(errorTextObserver, times(1)).onChanged("error")

    }

}