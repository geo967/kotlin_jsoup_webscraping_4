package com.example.jsoup4_kotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsoup4_kotlin.adapter.RecyclerViewAdapter
import com.example.jsoup4_kotlin.data.CurrencyData
import com.example.jsoup4_kotlin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getDataFromWeb()
    }

    private fun getDataFromWeb() {
        thread{
            val doc = Jsoup.connect("https://www.bookmyforex.com/us-dollar/rates/kochi/")
                .timeout(60000)
                .validateTLSCertificates(false)
                .get()

            val currencyGrid=doc.getElementsByClass("table table-striped euroRatestable static-rate")
            val currencyPrices=currencyGrid[0].getElementsByTag("tr")
          //  val currencyPrices1=doc.getElementsByTag("tbody")
         //   val currencyPrices2=currencyPrices1[0].getElementsByTag("tr")
         //   val currencyPrices=currencyPrices2[1].getElementsByTag("td")


            val currencyList=ArrayList<CurrencyData>()
            for(currencyPrice in currencyPrices){
               // val currencyPrices=currencyPrices1[1].getElementsByTag("td")
                currencyList.add(CurrencyData(currencyPrice.text().toString()))
            }

            this.runOnUiThread {
                val recyclerViewAdapter=RecyclerViewAdapter(currencyList,this)
                val linearLayoutManager=LinearLayoutManager(this)

                recyclerviewId.layoutManager=linearLayoutManager
                recyclerviewId.adapter=recyclerViewAdapter
            }
        }
    }
}