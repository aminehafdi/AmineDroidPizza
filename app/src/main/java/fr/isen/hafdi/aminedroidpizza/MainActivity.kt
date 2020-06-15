package fr.isen.hafdi.aminedroidpizza

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.preference.PreferenceManager
import android.widget.*
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.pub1))
        imageList.add(SlideModel(R.drawable.pub3))
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)


        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {


            val nom = getString("NOM", "")
            val pre = getString("PRENOM", "")

            editNom.setText(nom)
            editPre.setText(pre)



        }



        TimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                timeTv.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this,timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }


        val languages = resources.getStringArray(R.array.Pizza)

        ArrayAdapter.createFromResource(
            this,
            R.array.Pizza,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter
        }


    }


    fun onclickcommander(view: View) {

        val toast = Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG)
        val intent = Intent(this, ComfirmActivity::class.java)


        val nom = findViewById<EditText>(R.id.editNom)
        val name = nom.text.toString()
        intent.putExtra("Nom", name)

        val pre = findViewById<EditText>(R.id.editPre)
        val prenom = pre.text.toString()
        intent.putExtra("Prenom", prenom)

        val addr = findViewById<EditText>(R.id.editAddr)
        val adresse = addr.text.toString()
        intent.putExtra("Adresse", adresse)

        val pizzaselect = findViewById<Spinner>(R.id.spinner)
        val pizza = pizzaselect.selectedItem.toString()
        intent.putExtra("Pizza", pizza)

        val heureselect = findViewById<TextView>(R.id.timeTv)
        val heure = heureselect.text.toString()
        intent.putExtra("Heure", heure)


        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor
            .putString("NOM", editNom.text.toString())
            .putString("PRENOM", editPre.text.toString())
            .apply()



        fun validation() :Boolean{
            if(editNom.text.toString().isEmpty())
            {
                editNom.error = "Champ non valide"

            }
            if(editPre.text.toString().isEmpty())
            {
                editPre.error = "Champ non valide"


            }
            if(editAddr.text.toString().isEmpty())
            {
                editAddr.error = "Champ non valide"


            }
            if(editNum.text.toString().isEmpty())
            {
                editNum.error = "Champ non valide"


            }

            if(timeTv.text.toString().isEmpty())
            {
                timeTv.error = "Champ non valide"


            }
            if(editEmail.text.toString().isEmpty())
            {
                editEmail.error = "Champ non valide"
                return false

            }

            return true
        }

        if (validation()) {


            toast.show()
            startActivity(intent)

        }




    }
}




