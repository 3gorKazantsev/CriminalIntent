package com.egorkazantsev.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    init {
        for (i in 1..40) {
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = i % 3 == 0
            crime.requiresPolice = i % 5 == 0
            crimes += crime
        }
    }
}