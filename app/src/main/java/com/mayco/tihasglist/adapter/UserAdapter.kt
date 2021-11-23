package com.mayco.tihasglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mayco.tihasglist.R
import com.mayco.tihasglist.model.NomeDeUsuario
import com.mayco.tihasglist.utils.Constants.ANEL
import com.mayco.tihasglist.utils.Constants.TIAGO
import kotlin.properties.Delegates

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>(), Filterable {
    class UserViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(item: NomeDeUsuario){
            val employee = itemView.findViewById<TextView>(R.id.employee)
            val office = itemView.findViewById<TextView>(R.id.office)
            val github = itemView.findViewById<TextView>(R.id.github)
            val linkedin = itemView.findViewById<TextView>(R.id.linkedin)
            val image = itemView.findViewById<ImageView>(R.id.retangulo)


            employee.text = item.employee
            office.text = item.office
            github.text = item.github
            linkedin.text = item.linkedin
            image.setBackgroundResource(item.image)

        }
    }


    var items: List<NomeDeUsuario> by Delegates.observable(emptyList()) { _, old, new -> if (old != new) notifyDataSetChanged() }

    var NameList: List<NomeDeUsuario> by Delegates.observable(emptyList()) { _, old, new ->
        if (old != new) notifyDataSetChanged()
    }
    var filterList = ArrayList<NomeDeUsuario>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_perfil, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val atual = items[position]
        holder.bind(item = atual)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                    NameList as ArrayList<NomeDeUsuario>
                } else {
                    val resultList = ArrayList<NomeDeUsuario>()
                    for (row in NameList) {
                        if (row.employee.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                try {
                    items = results?.values as List<NomeDeUsuario>
                    notifyDataSetChanged()
                } catch (e: Exception) {
                    items = NameList
                    notifyDataSetChanged()
                }
            }
        }
    }
}