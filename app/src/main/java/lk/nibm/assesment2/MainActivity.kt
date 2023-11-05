package lk.nibm.assesment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var spnPet:Spinner
    lateinit var imageVIew:ImageView
//    lateinit var btnGenerate:Button


    val pets = arrayOf("Cat", "Dog")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spnPet =findViewById(R.id.spnPet)
        imageVIew=findViewById(R.id.imageView)
//        btnGenerate=findViewById(R.id.btnGenerate)

//        btnGenerate.setOnClickListener { loadPetInfo("Cat") }


        val petAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,pets)

        petAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnPet.adapter=petAdapter

        loadPetInfo("Cat")

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        loadPetInfo(pets[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun loadPetInfo(s: String) {
        val url = "https://random.dog/woof.json"
    }

}

