package com.cibertec.adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.MainActivity
import com.cibertec.entidad.Pelicula
import com.cibertec.holder.VistaPelicula
import java.util.ArrayList
import com.cibertec.R

class PeliculaAdaptador(val lista: ArrayList<Pelicula>):
    RecyclerView.Adapter<VistaPelicula>() {

        private lateinit var context: Context

        interface OnItemClickListener {
            fun onUpdate(pelicula: Pelicula)
            fun onDelete(pelicula: Pelicula)
        }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VistaPelicula {
        context = parent.context
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return VistaPelicula(item)
    }

    override fun onBindViewHolder(holder: VistaPelicula, position: Int) {
        val pelicula = lista[position]

        holder.tvNomPel.text = lista.get(position).nompel
        holder.tvCodigoDuracion.text = "Código: ${pelicula.codped} - Duración: ${pelicula.duracion} min"
        holder.tvAnioGenero.text = "Año: ${pelicula.anio} - Género: ${pelicula.genero}"

        val ID = context.resources.getIdentifier(pelicula.foto, "drawable", context.packageName)
        holder.imgPel.setImageResource(ID)

        // Botón Editar
        holder.btnUpdate.setOnClickListener {
            //val intent = Intent(context, ActualizarActivity::class.java)
            //intent.putExtra("pelicula", pelicula)
            //ContextCompat.startActivity(context, intent, null)
        }

        // Botón Eliminar
        holder.btnDelete.setOnClickListener {
            listener?.onDelete(pelicula)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("pelicula", lista.get(position))
            ContextCompat.startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}