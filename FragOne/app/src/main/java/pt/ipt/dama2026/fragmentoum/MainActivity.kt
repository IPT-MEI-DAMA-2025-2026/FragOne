package pt.ipt.dama2026.fragmentoum

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // variáveis para manipular os fragmentos
    private lateinit var f1: MyFragment
    private lateinit var f2: MyFragment
    private lateinit var f3: MyFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // instanciar os fragmentos
        f1 = MyFragment.novaInstancia(getString(R.string.txtFrag1),
                                      getString(R.string.btFrag1),
                                      1)
        f2 = MyFragment.novaInstancia(getString(R.string.txtFrag2), getString(R.string.btFrag2),2)
        f3 = MyFragment.novaInstancia(getString(R.string.txtFrag3), getString(R.string.btFrag3),3)

        // injetar estes três fragmentos no layout, na interface da app
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmento1, f1)
        fragmentTransaction.add(R.id.fragmento2, f2)
        fragmentTransaction.add(R.id.fragmento3, f3)
        fragmentTransaction.commit()

    }
}