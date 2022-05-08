package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pokemon.databinding.ActivityPokDetailsBinding

class pokDetails : AppCompatActivity() {

    lateinit var views:ActivityPokDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        views= ActivityPokDetailsBinding.inflate(layoutInflater)

        setContentView(views.root)

        var nombre=intent.getStringExtra("name")
        var especie=intent.getStringExtra("specie")
        var foto=intent.getStringExtra("foto")

        views.txtNombrePokemon.text=nombre.toString()
        views.txtEspecie.text=especie.toString()

        Glide
            .with(this)
            .load(foto)
            .centerCrop()
            .placeholder(R.drawable.who)
            .into(views.imageView);

    }
}