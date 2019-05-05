package com.marketpulsetask.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marketpulsetask.R
import com.marketpulsetask.adapter.ListAdapter
import com.marketpulsetask.databinding.ActivityMainBinding
import com.marketpulsetask.pojo.CriteriaItem
import com.marketpulsetask.pojo.Response
import com.marketpulsetask.remote.APIService
import com.marketpulsetask.ui.global.BaseView
import com.marketpulsetask.ui.view_model.MainViewModel
import com.marketpulsetask.ui.view_model.MainViewModelFactory
import com.marketpulsetask.utils.MyUtil
import com.marketpulsetask.utils.Navigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), ListAdapter.ItemClickCallback, BaseView {

    private val TAG = MainActivity::class.java.simpleName
    private var myAdapter: ListAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var responseDataList: ArrayList<Response>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBindings(savedInstanceState)
        initRecyclerView()
        myCompositeDisposable = CompositeDisposable()// initialize
        mViewModel.loadData()
    }


    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewModelProviders.of(this, MainViewModelFactory(this)).get(MainViewModel::class.java)
        if (savedInstanceState == null) {
            mViewModel = MainViewModel(this)
            mViewModel.init()
        }
        binding.viewModel = mViewModel
    }

    //Implement loadData
    private fun loadData() {


        val requestInterface = Retrofit.Builder()
            .baseUrl("https://mp-android-challenge.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
        Log.e("Respose", responseList.toString())
        responseDataList = ArrayList(responseList)
        myAdapter = ListAdapter(responseDataList!!, this)

//Set the adapter//

        recyclerView.adapter = myAdapter

    }

    override fun onItemClick(data: List<CriteriaItem>, position: Int) {
        Navigator.navigateToCriteriaListScreen(this, data)
        /* val data_list = data.criteria?.get(0)
         for (x in 0 until data_list?.variable!!.size) {
             println(data_list.variable.toString())
             Log.e("Response", data_list.variable.toString())
         }

         for ((key, value) in data_list.variable) {
             Log.e("Response", "$key = $value")
             Log.e("Response", "${value.values}")

         }
         Toast.makeText(this, "variable values", Toast.LENGTH_LONG).show()

         Toast.makeText(this, "You clicked: ${data.name}", Toast.LENGTH_LONG).show()*/



    }

    override fun onDestroy() {
        super.onDestroy()

//Clear all your disposables//

        myCompositeDisposable?.clear()

    }

    override fun onSuccess(objects: List<Response>) {

        try {
            responseDataList = ArrayList(objects)
            myAdapter = ListAdapter(responseDataList!!, this)
            recyclerView.adapter = myAdapter
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onError(objects: String?) {
        MyUtil.showToast(this, objects)
    }


}
