package io.skrape.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.skrape.core.htmlDocument

class MainViewModel : ViewModel() {

    private val _titleLiveData: MutableLiveData<String> = MutableLiveData()
    private val _itemsLiveData: MutableLiveData<List<String>> = MutableLiveData()

    val title: LiveData<String>
        get() = _titleLiveData

    val items: LiveData<List<String>>
        get() = _itemsLiveData

    fun parse(html: String) {
        val parsedHtml = htmlDocument(html)

        _titleLiveData.postValue(parsedHtml.titleText)
        parsedHtml.findAll("li")
            .map { it.text }
            .let(_itemsLiveData::postValue)
    }

}
