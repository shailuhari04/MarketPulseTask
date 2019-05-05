package com.marketpulsetask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.annotations.Until
import com.marketpulsetask.adapter.ListAdapter
import com.marketpulsetask.pojo.Response
import com.marketpulsetask.remote.APIService
import com.marketpulsetask.remote.ApiResponse
import com.marketpulsetask.remote.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class MainActivity : AppCompatActivity(), ListAdapter.ItemClickCallback {


    private var myAdapter: ListAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var responseDataList: ArrayList<Response>? = null
    private lateinit var recyclerView: RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        loadData()
    }


    //Initialise the RecyclerView//
    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview)

//Use a layout manager to position your items to look like a standard ListView//

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

    }


    //Implement loadData//

    private fun loadData() {

//Define the Retrofit request//

        val requestInterface = Retrofit.Builder()

//Set the API’s base URL//

            .baseUrl("https://mp-android-challenge.herokuapp.com/")

//Specify the converter factory to use for serialization and deserialization//

            .addConverterFactory(GsonConverterFactory.create())

//Add a call adapter factory to support RxJava return types//

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

//Build the Retrofit instance//

            .build().create(APIService::class.java)

//Add all RxJava disposables to a CompositeDisposable//

        myCompositeDisposable?.add(
            requestInterface.data1

//Send the Observable’s notifications to the main UI thread//

                .observeOn(AndroidSchedulers.mainThread())

//Subscribe to the Observer away from the main UI thread//

                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        )

    }

    private fun handleResponse(responseList: List<Response>) {

        responseDataList = ArrayList(responseList)
        myAdapter = ListAdapter(responseDataList!!, this)

//Set the adapter//

        recyclerView.adapter = myAdapter

    }

    override fun onItemClick(retroCrypto: Response) {

//If the user clicks on an item, then display a Toast//

        Toast.makeText(this, "You clicked: ${retroCrypto.name}", Toast.LENGTH_LONG).show()

    }

    override fun onDestroy() {
        super.onDestroy()

//Clear all your disposables//

        myCompositeDisposable?.clear()

    }

}
