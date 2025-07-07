package com.cibertec

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.adaptador.PeliculaAdaptador
import com.cibertec.controlador.PeliculaController
import com.cibertec.entidad.Pelicula
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etDuracion: EditText
    private lateinit var etAnio: EditText
    private lateinit var spinnerGenero: Spinner
    private lateinit var btnGrabar: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var controller: PeliculaController
    private lateinit var listaPeliculas: ArrayList<Pelicula>
    private lateinit var adaptador: PeliculaAdaptador
    private lateinit var Woman:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar vistas
        controller = PeliculaController()
        etNombre = findViewById(R.id.etNombre)
        etDuracion = findViewById(R.id.etDuracion)
        etAnio = findViewById(R.id.etAnio)
        spinnerGenero = findViewById(R.id.spinnerGenero)
        btnGrabar = findViewById(R.id.btnGrabar)
        recyclerView = findViewById(R.id.recyclerView)
        Woman = findViewById(R.id.Woman)

        val generos = arrayOf("Acción", "Terror", "Comedia", "Romántica", "Ciencia Ficción", "Drama")
        spinnerGenero.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, generos)

        // Configurar RecyclerView
        listaPeliculas = controller.findAll()
        adaptador = PeliculaAdaptador(listaPeliculas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptador

        btnGrabar.setOnClickListener {
            grabarPelicula()
        }

        Woman.setOnClickListener {
            val navegar = Intent(this,IntroActivity::class.java)
            startActivity(navegar)
        }

    }

    private fun grabarPelicula() {
        val nombre = etNombre.text.toString().trim()
        val duracionStr = etDuracion.text.toString()
        val anioStr = etAnio.text.toString()
        val genero = spinnerGenero.selectedItem as String

        if (nombre.isEmpty() || duracionStr.isEmpty() || anioStr.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "Complete todos los campos", Snackbar.LENGTH_SHORT).show()
            return
        }

        val duracion = duracionStr.toIntOrNull()
        val anio = anioStr.toIntOrNull()

        if (duracion == null || anio == null) {
            Snackbar.make(findViewById(android.R.id.content), "Ingrese valores numéricos válidos", Snackbar.LENGTH_SHORT).show()
            return
        }

        Thread {
            val pelicula = Pelicula(0, nombre, duracion, anio, genero, "ic_launcher_background")
            controller.adicionarPelicula(pelicula)

            runOnUiThread {
                Snackbar.make(findViewById(android.R.id.content), "Película guardada", Snackbar.LENGTH_SHORT).show()
                limpiarCampos()
                refrescarLista()
            }
        }.start()
    }

    private fun limpiarCampos() {
        etNombre.setText("")
        etDuracion.setText("")
        etAnio.setText("")
        spinnerGenero.setSelection(0)
    }

    private fun refrescarLista() {
        listaPeliculas.clear()
        listaPeliculas.addAll(controller.findAll())
        adaptador.notifyDataSetChanged()
    }


}