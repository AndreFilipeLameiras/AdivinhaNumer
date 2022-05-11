package pt.ipg.adivinhanumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var numeroAdivinhar: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numeroAdivinhar = Random.nextInt(NUMERO_ADIVINHAR_MINIMO, NUMERO_ADIVINHAR_MAXIMO + 1)

        findViewById<Button>(R.id.buttonAdivinhar).setOnClickListener {
            adivinha()
        }
    }

    private fun adivinha() {
        
    }


    companion object{
        const val NUMERO_ADIVINHAR_MINIMO = 1
        const val NUMERO_ADIVINHAR_MAXIMO = 10
    }
}