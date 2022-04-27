package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pokemon.databinding.ActivityMainBinding
import java.lang.ref.ReferenceQueue

class MainActivity : AppCompatActivity(), adapterPokemon.OnItemClickListener {

    lateinit var layout:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout= ActivityMainBinding.inflate(layoutInflater)

        setContentView(layout.root)

        val lista=layout.list
        val btnAdd=layout.add
        val btnDelete=layout.delete
        val boxSearch=layout.search

        val pokemones:ArrayList<Pokedesk> = ArrayList()
        val adapter:adapterPokemon

        pokemones.add(Pokedesk("Bulbasaur","Planta","Latigo Sepa","https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"))
        pokemones.add(Pokedesk("Charmander","Fuego","---","https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"))
        pokemones.add(Pokedesk("Squartel","Agua","xxx","https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"))
        pokemones.add(Pokedesk("Piggeot","Aire","yyy","https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"))
        pokemones.add(Pokedesk("Caterpi","Insecto","zzz","https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"))

        adapter= adapterPokemon(pokemones,this)
        lista.layoutManager=LinearLayoutManager(this)
        lista.adapter=adapter


        val colaPeticiones= Volley.newRequestQueue(this)

        val url="https://pokeapi.co/api/v2/pokemon/"

        val request= StringRequest(Request.Method.GET,url,
            Response.Listener {  },
            Response.ErrorListener {  }
            )

        colaPeticiones.add(request)

    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }
}