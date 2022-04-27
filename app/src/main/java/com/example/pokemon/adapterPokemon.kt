package com.example.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.databinding.ItemsBinding

class adapterPokemon(var listPokemon:ArrayList<Pokedesk>, var listener:OnItemClickListener): RecyclerView.Adapter<adapterPokemon.contentViews>() {

    var lstOriginal:ArrayList<Pokedesk> = ArrayList()

    init {
        lstOriginal.addAll(listPokemon)
    }

    inner class contentViews(v: ItemsBinding):RecyclerView.ViewHolder(v.root), View.OnClickListener {

        val itemNombre:TextView
        val itemDesc:TextView
        val itemAtaques:TextView
        val itemPokemon:ImageView

        init {

            itemNombre=v.txtNombre
            itemDesc=v.txtDescripcion
            itemAtaques=v.txtAtaques
            itemPokemon=v.imgPokemon

            v.root.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            listener.onItemClick(adapterPosition)
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contentViews {
        val views=ItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return contentViews(views)
    }

    override fun onBindViewHolder(holder: contentViews, position: Int) {
        val item=listPokemon.get(position)

        holder.itemNombre.text=item.nombre
        holder.itemDesc.text=item.especie
        holder.itemAtaques.text=item.ataques

        Glide
            .with(holder.itemNombre.context)
            .load(item.Foto)
            .centerCrop()
            .placeholder(R.drawable.who)
            .into(holder.itemPokemon);

    }

    override fun getItemCount()=listPokemon.size

    fun buscar(str:String){

        if(str.isEmpty())
        {
            listPokemon.clear()
            listPokemon.addAll(lstOriginal)

        }else{
            var result:ArrayList<Pokedesk> = ArrayList()

            for(item in listPokemon){
                if(item.nombre?.lowercase()?.contains(str.lowercase())!!){
                    result.add(item)
                }

            }

            listPokemon.clear()
            listPokemon.addAll(result)


        }

        notifyDataSetChanged()

    }
}