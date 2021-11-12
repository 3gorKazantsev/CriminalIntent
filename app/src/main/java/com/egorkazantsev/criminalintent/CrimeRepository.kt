package com.egorkazantsev.criminalintent

import android.content.Context
import androidx.room.Room
import com.egorkazantsev.criminalintent.database.CrimeDatabase
import com.egorkazantsev.criminalintent.database.migration_1_2
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context) {

    // properties
    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2)
        .build()

    private val crimeDao = database.crimeDao()

    private val executor = Executors.newSingleThreadExecutor()

    // functions
    fun getCrimes() = crimeDao.getCrimes()

    fun getCrime(id: UUID) = crimeDao.getCrime(id)

    fun updateCrime(crime: Crime) {
        executor.execute {
            crimeDao.updateCrime(crime)
        }
    }

    fun insertCrime(crime: Crime) {
        executor.execute {
            crimeDao.insertCrime(crime)
        }
    }

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null)
                INSTANCE = CrimeRepository(context)
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialize")
        }
    }
}