package com.example.jsoup4_kotlin.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jsoup4_kotlin.R
import com.example.jsoup4_kotlin.data.CurrencyData
import kotlinx.android.synthetic.main.item_design.view.*

class RecyclerViewAdapter(private val currencyList:List<CurrencyData>,private val activity: Activity):
    RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_design,parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val itemPos=currencyList[position]
        holder.bindSet(itemPos,activity)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }



    class MyHolder(v: View):RecyclerView.ViewHolder(v),View.OnClickListener{

        private var view:View=v
        private var currencyList:CurrencyData?=null
        private var act:Activity?=null

        override fun onClick(p0: View?) {

        }

        init {
            v.setOnClickListener(this)
        }

        fun bindSet(currencyList:CurrencyData,activity:Activity){
            this.currencyList=currencyList
            this.act=activity

            view.textId.text=currencyList.price.toString()
        }

    }
}