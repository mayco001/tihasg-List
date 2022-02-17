package com.mayco.tihasglist

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mayco.tihasglist.adapter.UserAdapter
import com.mayco.tihasglist.model.NomeDeUsuario
import com.mayco.tihasglist.utils.Constants.ANEL
import com.mayco.tihasglist.utils.Constants.FELIPE
import com.mayco.tihasglist.utils.Constants.FERNANDA
import com.mayco.tihasglist.utils.Constants.LUIZ
import com.mayco.tihasglist.utils.Constants.MICHAEL
import com.mayco.tihasglist.utils.Constants.TIAGO

private var pesquisa: SearchView? = null
private var adapterUser = UserAdapter()
private var listaName: RecyclerView? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names: List<NomeDeUsuario> = listOf(
            NomeDeUsuario(
                employee = TIAGO,
                image = R.drawable.tiago,
                github = getString(R.string.tiagoGithub),
                linkedin = "",
                office = getString(R.string.androidCM)
            ),
            NomeDeUsuario(
                employee = ANEL,
                image = R.drawable.anel,
                github = getString(R.string.anelGithub),
                linkedin = getString(R.string.anelLinkedin),
                office = getString(R.string.androidF)
            ),
            NomeDeUsuario(
                employee = FELIPE,
                image = R.drawable.felipe,
                github = "",
                linkedin = getString(R.string.felipeLinkedin),
                office = getString(R.string.androidM)
            ),
            NomeDeUsuario(
                employee = FERNANDA,
                image = R.drawable.fernanda,
                github = "",
                linkedin = getString(R.string.fernandaLinkedin),
                office = getString(R.string.androidF)

            ),
            NomeDeUsuario(
                employee = LUIZ,
                image = R.drawable.luiz,
                github = "",
                linkedin = getString(R.string.luizLinkedin),
                office = getString(R.string.androidM)
            ),
            NomeDeUsuario(
                employee = MICHAEL,
                image = R.drawable.ic_supervised_user,
                github = getString(R.string.michaelGithub),
                linkedin = getString(R.string.michaelLinkedin),
                office = getString(R.string.androidM)
            ),

        )

        listaName = findViewById(R.id.recy)
        pesquisa = findViewById(R.id.btn_search)

        adapterUser.items = names
        adapterUser.nameList = names
        seturRecyclerView()
        adapterUser.notifyDataSetChanged()

        pesquisa?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapterUser.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterUser.filter.filter(newText)
                return true
            }
        })
        seturRecyclerView()
    }

    private fun seturRecyclerView() {
        listaName?.adapter = adapterUser
        val manager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
        listaName?.layoutManager = manager
    }
}
