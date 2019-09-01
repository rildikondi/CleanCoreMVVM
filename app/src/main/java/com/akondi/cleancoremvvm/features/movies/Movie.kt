package com.akondi.cleancoremvvm.features.movies

import com.akondi.cleancoremvvm.core.extension.empty

data class Movie(val id: Int, val poster: String) {

    companion object {
        fun empty() = Movie(0, String.empty())
    }
}