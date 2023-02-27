package com.example.kapp2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kapp2.R
import com.example.kapp2.databinding.ItemBotonBinding
import com.example.kapp2.model.Boton

class Kapp2Adapter: RecyclerView.Adapter<Kapp2Adapter.Kapp2ViewHolder>() {

    var listaBotones: List<Boton>?=null
    var onBotonClickListener:OnBotonClickListener?=null

    inner class Kapp2ViewHolder(val binding: ItemBotonBinding)
        : RecyclerView.ViewHolder(binding.root){
        init {

            binding.tbtLeft.setOnClickListener(){
                //recuperamos la tarea de la lista
                val boton= listaBotones?.get(this.adapterPosition)
                //llamamos al evento borrar que estará definido en el fragment
                onBotonClickListener?.onBotonClik(boton)
            }
            /*//inicio del click sobre el Layout(constraintlayout)
            binding.tbtRight.setOnClickListener(){
                val tarea= listaTareas?.get(this.adapterPosition)
                onTareaClickListener?.onTareaClick(tarea)
            }


             */

            /*
            binding.tbtLeft.setOnLongClickListener() {

            }

             */
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            Kapp2ViewHolder {
        //utilizamos binding, en otro caso hay que indicar el item.xml. Para más detalles puedes verlo en la documentación
        val binding = ItemBotonBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return Kapp2ViewHolder(binding)
    }

    override fun onBindViewHolder(kapp2ViewHolder: Kapp2ViewHolder, pos: Int) {
        //Nos pasan la posición del item a mostrar en el viewHolder
        with(kapp2ViewHolder) {
            //cogemos la tarea a mostrar y rellenamos los campos del ViewHolder
            with(listaBotones!![pos]) {

                //mostramos el icono en función del estado
                binding.tbtLeft.setBackgroundResource(
                    when (tematica) {
                        1 -> R.drawable.ic_tg_red_bg
                        2 -> R.drawable.ic_tg_skyblue_bg
                        3 -> R.drawable.ic_tg_yellow_bg
                        4 -> R.drawable.ic_tg_green_bg
                        5 -> R.drawable.ic_tg_purple_bg
                        else -> R.drawable.ic_tg_red_bg
                    }
                )
            }
        }
    }

    override fun getItemCount(): Int = listaBotones?.size?:0


    interface OnBotonClickListener{
        //editar tarea que contiene el ViewHolder
        fun onBotonClik(boton: Boton?)

    }
}