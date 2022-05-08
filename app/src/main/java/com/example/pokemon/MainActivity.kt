package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pokemon.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.lang.ref.ReferenceQueue
import java.lang.reflect.Array

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
        var adapter:adapterPokemon
        val colaPeticiones= Volley.newRequestQueue(this)

        val url="https://pokeapi.co/api/v2/pokemon?offset=150&limit=150"

        val request= JsonObjectRequest(url,
            Response.Listener {
                //Log.i("result",it.get("results").toString())
                val arrayObj=JSONArray(it.get("results").toString())
                var objPok:JSONObject

                for(obj in 0 until 50)
                {

                    objPok=JSONObject(arrayObj.get(obj).toString())
                    var especie=""

                    /************************************************************/
                    val peticionPokemon= Volley.newRequestQueue(this)

                    val rDetails= JsonObjectRequest(objPok.get("url").toString(),
                        Response.Listener {
                            //CONVIRTIENDO A OBJETO LA PROPIEDAD TYPES
                            val obj1= JSONArray(it.get("types").toString())

                            //CONVIRTIENDO A OBJETO EL 1° ELEMENTO DE LA PROPIEDAD TYPES(OBJETO)
                            val obj2=JSONObject(obj1.get(0).toString())

                            //CONVIRTIENDO A LA PROPIEDAD TYPE
                            val obj3=JSONObject(obj2.get("type").toString())
                            especie=obj3.get("name").toString()

                            //OBTENER FOTO DE PROPIEDAD SPRITE
                            val sprites=JSONObject(it.get("sprites").toString())

                            //OBTENER LOS ATAQUES
                            val ataques= JSONArray(it.get("moves").toString())
                            Log.i("result",ataques.length().toString())
                            var strAtaques=""
                            for (index in 0 until ataques.length())
                            {
                                if(index>=10)
                                    break

                                val ataque=JSONObject(ataques.get(index).toString())
                                val nameAtaque=JSONObject(ataque.get("move").toString())
                                strAtaques +=" ${nameAtaque.get("name").toString()}"
                            }

                            pokemones.add(Pokedesk(it.get("name").toString(),especie,strAtaques,sprites.get("front_default").toString()))
                            adapter= adapterPokemon(pokemones,this)
                            lista.layoutManager=LinearLayoutManager(this)
                            lista.adapter=adapter

                        },
                        Response.ErrorListener {
                            Log.e("result",it.toString())
                        }
                    )

                    peticionPokemon.add(rDetails)
                    /************************************************************/

                }



            },
            Response.ErrorListener {
                Log.e("result",it.toString())
            }
            )

        colaPeticiones.add(request)

    }

    override fun onItemClick(position: Int) {
        Log.i("result","Click")
    }
}