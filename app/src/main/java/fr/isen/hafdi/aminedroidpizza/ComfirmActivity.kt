package fr.isen.hafdi.aminedroidpizza

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import java.util.ArrayList

class ComfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comfirm)

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.pub1))
        imageList.add(SlideModel(R.drawable.pub3))
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        val intent = intent


        val nom = intent.getStringExtra("Nom")
        val prenom = intent.getStringExtra("Prenom")
        val resultattv = findViewById<TextView>(R.id.prenomtv)
        resultattv.text = prenom+" "+nom

        val adresse = intent.getStringExtra("Adresse")

        val addrtv = findViewById<TextView>(R.id.adressetv)
        addrtv.text = adresse

        val pizza = intent.getStringExtra("Pizza")
        val pizzatv = findViewById<TextView>(R.id.Pizzatv)
        pizzatv.text = pizza

        val heure = intent.getStringExtra("Heure")
        val heuretv = findViewById<TextView>(R.id.heuretv)
        heuretv.text = heure
    }

    fun BtnConfirmer(view: View) {

            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:marc.mollinari@gmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation commande ")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Votre commande a bien été enregistrée")

            startActivity(Intent.createChooser(emailIntent, "Email Commande"))

    }
}