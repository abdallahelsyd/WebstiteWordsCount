package com.instabug.instabugwordscount.domain.repositories

import com.instabug.localprefs.Constants
import com.instabug.localprefs.MyPreference
import com.instabug.localprefs.general.PrefsStoreImpl
import com.instabug.remote.Network
import removePunctuation
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val myPreference: MyPreference
): IContentRepository {

    override fun getWebsiteContent(callback: (success: List<String>, error: String) -> Unit) {
        val getHtml = Network.get(Constants.STORE_NAME)
        if (getHtml.isNotEmpty()){
            myPreference.saveData(getHtml)
            val words = getHtml.removePunctuation()
            callback(words,"")

        }else{
            callback(emptyList(),"Network Error")
        }

    }
}