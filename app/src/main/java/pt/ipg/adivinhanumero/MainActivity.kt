package pt.ipg.adivinhanumero

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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

        novoJogo()
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


        if(numero == numeroAdivinhar){
            val dialogoAlerta = AlertDialog.Builder(this)
            dialogoAlerta.setTitle(R.string.jogar_novamente)
            dialogoAlerta.setCancelable(false)
            dialogoAlerta.setMessage(getString(R.string.mensagem_novo_jogo))
            dialogoAlerta.setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, which -> novoJogo() })
            dialogoAlerta.setNegativeButton(android.R.string.cancel , DialogInterface.OnClickListener { dialog, which -> finish() } )
            dialogoAlerta.show()
        }
    }

    private fun novoJogo() {
        numeroAdivinhar = Random.nextInt(NUMERO_ADIVINHAR_MINIMO, NUMERO_ADIVINHAR_MAXIMO + 1)
        tentativas = 0
        jogos++

        mostraTentativas()
        mostraJogo()
        findViewById<TextView>(R.id.textViewMensagem).text = ""

    }

    private fun mostraTentativas() {
        textViewTentativas!!.text = getString(R.string.Tentativas) + ": $tentativas"
    }

    private fun mostraJogo() {
        textViewJogos!!.text = getString(R.string.Jogo) + ":  $jogos"
    }


    companion object{
        const val NUMERO_ADIVINHAR_MINIMO = 1
        const val NUMERO_ADIVINHAR_MAXIMO = 10
    }
}