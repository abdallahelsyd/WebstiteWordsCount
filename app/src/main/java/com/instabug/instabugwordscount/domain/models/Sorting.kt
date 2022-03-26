package com.instabug.instabugwordscount.domain.models

sealed class Sorting{
    object Ascending:Sorting()
    object Descending:Sorting()
}
