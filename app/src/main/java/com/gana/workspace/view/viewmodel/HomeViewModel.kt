package com.gana.workspace.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gana.workspace.view.model.HomeModel

class HomeViewModel : ViewModel() {

    private val _homeModels = MutableLiveData<List<HomeModel>>()
    val homeModels: LiveData<List<HomeModel>> get() = _homeModels

    init {
        loadHomeModels()
    }

    private fun loadHomeModels() {
        val models = listOf(
            HomeModel("YouChat", "https://you.com/chat"),
            HomeModel("Perplexity AI", "https://www.perplexity.ai"),
            HomeModel("Pi", "https://pi.ai"),
            HomeModel("Claude", "https://claude.ai"),
            HomeModel("Poe", "https://poe.com"),
            HomeModel("Character.ai", "https://beta.character.ai"),
            HomeModel("Replika", "https://replika.com"),
            HomeModel("OpenRouter Chat", "https://chat.openrouter.ai"),
            HomeModel("Ora.ai", "https://ora.ai"),
            HomeModel("Notion AI", "https://www.notion.so/product/ai"),
            HomeModel("ChatSonic", "https://writesonic.com/chat"),
            HomeModel("Jasper Chat", "https://www.jasper.ai/chat"),
            HomeModel("Tavily AI", "https://www.tavily.com"),
            HomeModel("HuggingChat", "https://huggingface.co/chat"),
            HomeModel("Komo AI", "https://www.komo.ai"),
            HomeModel("Forefront AI", "https://chat.forefront.ai"),
            HomeModel("DeepSeek Chat", "https://chat.deepseek.com"),
            HomeModel("TinyWow AI", "https://tinywow.com/tools/ai-chat"),
            HomeModel("Phind", "https://www.phind.com"),
            HomeModel("ChatGPT", "https://chat.openai.com")
        )



        _homeModels.value = models
    }
}
