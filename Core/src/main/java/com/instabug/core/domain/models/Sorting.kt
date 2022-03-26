package com.instabug.core.domain.models

sealed class Sorting{
    object Ascending: Sorting()
    object Descending: Sorting()
}
