package xyz.sachonidas.chc2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.view.View

class Home : AppCompatActivity() {

    var calculadoraCard: CardView? = null
    var historialCard: CardView? = null
    var estadisticaCard: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        calculadoraCard.setOnClickListener {
            val calculadora = Intent(this, MainActivity::class.java)
            calculadora.putExtra("select", 1)
            startActivity(calculadora)
        }
        historialCard.setOnClickListener {
            val historial = Intent(this, MainActivity::class.java)
            historial.putExtra("select", 2)
            startActivity(historial)
        }
        estadisticaCard.setOnClickListener {
            val estadistica = Intent(this, MainActivity::class.java)
            estadistica.putExtra("select", 3)
            startActivity(estadistica)
        }


    }
}
