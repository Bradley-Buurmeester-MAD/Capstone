package com.example.capstone.ui.quote

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.capstone.repository.QuoteRepository
import kotlinx.coroutines.launch

class QuoteViewModel(application: Application) : AndroidViewModel(application) {

    private val quoteRepository = QuoteRepository()

    /**
     * This property points direct to the LiveData in the repository
     */
    val quote = quoteRepository.quote

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from view for error showing
     */
    val errorText: LiveData<String>
        get() = _errorText

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getRandomQuote() {
        viewModelScope.launch {
            try {
                quoteRepository.getQuoteOfTheDay()
            } catch (error: QuoteRepository.TriviaRefreshError) {
                _errorText.value = error.message
                Log.e("Quote error", error.cause.toString())
            }
        }
    }

}