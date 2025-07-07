package com.cibertec.data

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.cibertec.utils.appConfig

class InitBD: SQLiteOpenHelper(appConfig.CONTEXT, appConfig.DB_NAME, null, appConfig.VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE Peliculas (
                    codpel INTEGER PRIMARY KEY AUTOINCREMENT,
                    nompel TEXT NOT NULL,
                    duracion INTEGER NOT NULL,
                    anio INTEGER NOT NULL,
                    genero TEXT NOT NULL,
                    foto varchar(20)
                )
            """.trimIndent()
        )

        db.execSQL("insert into Peliculas values(null, 'Chucky', 180, 2010, 'Terror', 'p1')")

    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS Peliculas")
        onCreate(db)
    }

}