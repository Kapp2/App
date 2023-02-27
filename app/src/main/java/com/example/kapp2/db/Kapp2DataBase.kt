package com.example.kapp2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

@Database(entities = [Boton::class], [Perfil::class], version = 1, exportSchema = false)
abstract class Kapp2DataBase : RoomDatabase(){

    abstract fun perfilDAO(): PerfilDAO
    abstract fun botonDao(): BotonDAO

        companion object {
            // Singleton prevents multiple instances of database opening at the same time.
            @Volatile
            private var INSTANCE: Kapp2DataBase? = null

            fun getDatabase(context: Context): Kapp2DataBase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        Kapp2DataBase::class.java,
                        "tareas_database")
                        .addCallback(InicioDbCallback())
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }

        //***************CallBack******************************
        /**
         * Permite iniciar la base de datos con Tareas
         */
        private class InicioDbCallback() : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    GlobalScope.launch {
                        cargarDatabase(database.botonDao(),database.perfilDAO())
                    }
                }
            }
            //Iniciamos la base de datos con Tareas de ejemplo
            suspend fun cargarDatabase(botonDAO: BotonDAO, perfilDAO: PerfilDAO) {
                val tecnicos = listOf(
                    "Pepe Gotero",
                    "Sacarino Pómez",
                    "Mortadelo Fernández",
                    "Filemón López",
                    "Zipi Climent",
                    "Zape Gómez"
                )
                lateinit var perfil: Perfil
                 {

                    perfil = Perfil(
                        "Master",
                        "123456",

                    )
                    perfilDAO.addPerfil(perfil)
                }
                lateinit var boton: Boton
                (1..10).forEach {
                    // Thread.sleep(2000)
                    boton = Boton(
                        "Master",
                        "123456",

                        )
                    botonDAO.addBoton(boton)
                }
            }
        }

    }