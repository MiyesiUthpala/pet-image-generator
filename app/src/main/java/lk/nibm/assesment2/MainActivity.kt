package lk.nibm.assesment2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.logging.Handler

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var spnPet:Spinner
    lateinit var imageVIew:ImageView
    lateinit var btnGenerate:Button


    val pets = arrayOf("Cat", "Dog")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spnPet =findViewById(R.id.spnPet)
        imageVIew=findViewById(R.id.imageView)
        btnGenerate=findViewById<Button>(R.id.btnGenerate)

        btnGenerate.setOnClickListener {
            loadPetInfo(spnPet.selectedItem.toString())
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()

        }


        val petAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,pets)

        petAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnPet.adapter=petAdapter



    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun loadPetInfo(type: String) {

        var url =""
        if (type == "Cat"){
            url="https://api.thecatapi.com/v1/images/search"
            val request = JsonArrayRequest(com.android.volley.Request.Method.GET, url, null, Response.Listener { response ->

                try {

                    var imageUrl = JSONObject(response.get(0).toString()).getString("url")
                    Picasso.get().load(imageUrl).into(imageVIew)
                    println(url)
                } catch (e: JSONException) {
                    println("Error parsing JSON: ${e.message}")
                }
            }) { error ->
                println("Volley error: ${error.message}")
            }
            Volley.newRequestQueue(this).add(request)

        }
        else{
            url = "https://random.dog/woof.json"
            val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
                try {

                    var imageUrl = response.getString("url")
                    Picasso.get().load(imageUrl).into(imageVIew)
                    println(url)
                } catch (e: JSONException) {
                    println("Error parsing JSON: ${e.message}")
                }
            }, { error ->
                println("Volley error: ${error.message}")
            })
            Volley.newRequestQueue(this).add(request)

        }

        }

    }

//}

