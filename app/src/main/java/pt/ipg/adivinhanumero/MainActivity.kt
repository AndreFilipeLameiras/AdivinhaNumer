package pt.ipg.adivinhanumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        val ediTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val numero = ediTextNumber.text.toString().toIntOrNull()

        if(numero !in NUMERO_ADIVINHAR_MINIMO..NUMERO_ADIVINHAR_MAXIMO){
            ediTextNumber.error = getString(R.string.numero_invalido)
            ediTextNumber.requestFocus()
            return
        }


        val mensagem = when{
            numero!! < numeroAdivinhar -> getString(R.string.numero_maior)
            numero!! > numeroAdivinhar -> getString(R.string.numero_menor)
            else -> getString(R.string.Acertou)

        }
        findViewById<TextView>(R.id.textViewMensagem).text = mensagem


    }


    companion object{
        const val NUMERO_ADIVINHAR_MINIMO = 1
        const val NUMERO_ADIVINHAR_MAXIMO = 10
    }
}