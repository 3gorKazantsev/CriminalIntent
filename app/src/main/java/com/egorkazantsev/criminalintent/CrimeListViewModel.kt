package com.egorkazantsev.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {

    private val crimeRepository = CrimeRepository.get()

    val crimeListLiveData = crimeRepository.getCrimes()

    fun addCrime(crime: Crime) {
        crimeRepository.insertCrime(crime)
    }
}