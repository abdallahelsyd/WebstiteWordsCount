package com.instabug.core.domain.repositories

import com.instabug.core.common.Constants
import com.instabug.localprefs.MyPreference
import com.instabug.remote.Network
import com.instabug.core.utils.removePunctuation
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val myPreference: MyPreference
): IContentRepository {
    override fun getWebsiteContent(callback: (success: List<String>, error: Exception?) -> Unit) {
        Network.get(Constants.URL, callback = {htmlText,e->
            if (htmlText.isNotEmpty()){
                myPreference.saveData(htmlText)
                val words = htmlText.removePunctuation()
                callback(words,e)
            }else{
                val localData=myPreference.getData()
                if (localData.isNotEmpty())
                    callback(localData.removePunctuation(),e)
                else
                    callback(emptyList(),e)
            }
        })
    }
}