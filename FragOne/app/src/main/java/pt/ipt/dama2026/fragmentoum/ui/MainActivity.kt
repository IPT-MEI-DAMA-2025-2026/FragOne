package pt.ipt.dama2026.fragmentoum.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pt.ipt.dama2026.fragmentoum.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // instanciar os fragmentos, criando uma lista de fragments
        val fragments = listOf(
            MyFragment.Companion.novaInstancia(
                getString(R.string.txtFrag1),
                getString(R.string.btFrag1),
                1
            ),
            MyFragment.Companion.novaInstancia(
                getString(R.string.txtFrag2),
                getString(R.string.btFrag2),
                2
            ),
            MyFragment.Companion.novaInstancia(
                getString(R.string.txtFrag3),
                getString(R.string.btFrag3),
                3
            )
        )

        // Adicionar fragments ao layout
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmento1, fragments[0])
            add(R.id.fragmento2, fragments[1])
            add(R.id.fragmento3, fragments[2])
        }.commit()

    }

}