package pt.ipg.adivinhanumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var numeroAdivinhar = 0
    private var tentativas = 0
    private var jogos = 1

    private var textViewTentativas : TextView? = null
    private var textViewJogos: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numeroAdivinhar = Random.nextInt(NUMERO_ADIVINHAR_MINIMO, NUMERO_ADIVINHAR_MAXIMO + 1)

        findViewById<Button>(R.id.buttonAdivinhar).setOnClickListener {
            adivinha()
        }

        textViewTentativas = findViewById<TextView>(R.id.textViewTentativas)
        textViewJogos = findViewById<TextView>(R.id.textViewJogos)
        mostraTentativas()
        mostraJogo()
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

        tentativas++
        mostraTentativas()
    }

    private fun mostraTentativas() {
        textViewTentativas!!.text = getString(R.string.Tentativas) + ": $tentativas"
    }

    private fun mostraJogo() {
        textViewJogos!!.text = getString(R.string.Jogo)+ "$jogos"
    }


    companion object{
        const val NUMERO_ADIVINHAR_MINIMO = 1
        const val NUMERO_ADIVINHAR_MAXIMO = 10
    }
}