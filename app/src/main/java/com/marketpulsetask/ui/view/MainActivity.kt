package com.marketpulsetask.ui.view

import android.graphics.Color
import android.os.Bundle
import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marketpulsetask.R
import com.marketpulsetask.adapter.CriteriaListAdapter
import com.marketpulsetask.adapter.ListAdapter
import com.marketpulsetask.adapter.VariableListAdapter
import com.marketpulsetask.databinding.ActivityMainBinding
import com.marketpulsetask.pojo.CriteriaItem
import com.marketpulsetask.pojo.Response
import com.marketpulsetask.pojo.VariableItems
import com.marketpulsetask.remote.APIService
import com.marketpulsetask.ui.global.BaseView
import com.marketpulsetask.ui.global.RecyclerViewItemClickListener
import com.marketpulsetask.ui.view_model.MainViewModel
import com.marketpulsetask.ui.view_model.MainViewModelFactory
import com.marketpulsetask.utils.Constants
import com.marketpulsetask.utils.MyUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_item_parent.*
import kotlinx.android.synthetic.main.row_item_value_variable.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewItemClickListener, BaseView {


    private val TAG = MainActivity::class.java.simpleName
    private var parentAdapter: ListAdapter? = null
    private var criteriaAdapter: CriteriaListAdapter? = null
    private var variableAdapter: VariableListAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var responseDataList: ArrayList<Response>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel
    private var visibleListType: Int = 1

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


    private fun handleResponse(responseList: List<Response>) {
        Log.e("Respose", responseList.toString())
        responseDataList = ArrayList(responseList)
        parentAdapter = ListAdapter(responseDataList!!, this)

//Set the adapter//

        recyclerView.adapter = parentAdapter

    }

    override fun parentItemClicked(criteriaData: MutableList<CriteriaItem>, position: Int) {
        visibleListType = 2 //manage level
        val color = responseDataList!![position].color

        tvName.apply {
            visibility = View.VISIBLE
            text = responseDataList!![position].name

        }
        tvTag.apply {
            visibility = View.VISIBLE
            text = responseDataList!![position].tag
            if (color != null) {
                if (color.equals(Constants.COLOR_GREEN, ignoreCase = true)) {
                    setTextColor(Color.GREEN)

                } else if (color.equals(Constants.COLOR_RED, ignoreCase = true)) {
                    setTextColor(Color.RED)

                }
            }

        }
        criteriaAdapter = CriteriaListAdapter(criteriaData, this)
        recyclerView.adapter = criteriaAdapter
    }


    override fun criteriaItemClicked(variable: HashMap<String, VariableItems>, position: Int) {
        visibleListType = 3 //manage level
        tvName.apply {
            visibility = View.GONE

        }
        tvTag.apply {
            visibility = View.GONE
        }
        variableAdapter = VariableListAdapter(variable, this)
        recyclerView.adapter = variableAdapter
    }

    override fun variableItemClicked(values: List<Float>, position: Int) {
        Toast.makeText(this, values[position].toString(), Toast.LENGTH_SHORT).show()
        tv_value.makeLinks(
            Pair("Terms of Service", View.OnClickListener {
                Toast.makeText(applicationContext, "Terms of Service Clicked", Toast.LENGTH_SHORT).show()
            }),
            Pair("Privacy Policy", View.OnClickListener {
                Toast.makeText(applicationContext, "Privacy Policy Clicked", Toast.LENGTH_SHORT).show()
            }))
    }

    override fun onBackPressed() {
        when (visibleListType) {
            1 -> {
                super.onBackPressed()
            }
            2 -> {
                try {
                    parentAdapter = ListAdapter(responseDataList!!, this)
                    recyclerView.adapter = parentAdapter
                } catch (e: java.lang.Exception) {

                }
            }
            3 -> {
                try {
                    parentAdapter = ListAdapter(responseDataList!!, this)
                    recyclerView.adapter = parentAdapter
                } catch (e: java.lang.Exception) {

                }
            }
        }

    }

    fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
        val spannableString = SpannableString(this.text)
        for (link in links) {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    Selection.setSelection((view as TextView).text as Spannable, 0)
                    view.invalidate()
                    link.second.onClick(view)
                }
            }
            val startIndexOfLink = this.text.toString().indexOf(link.first)
            spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        this.movementMethod = LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
        this.setText(spannableString, TextView.BufferType.SPANNABLE)
    }

    /*  override fun onItemClick(data: MutableMap<String, VariableItems>, position: Int) {
          val color = responseDataList!!.get(position).color

          tvName.apply {
              visibility = View.VISIBLE
              text = responseDataList!![position].name

          }
          tvTag.apply {
              visibility = View.VISIBLE
              text = responseDataList!![position].tag
              if (color != null) {
                  if (color.equals(Constants.COLOR_GREEN, ignoreCase = true)) {
                      setTextColor(Color.GREEN)

                  } else if (color.equals(Constants.COLOR_RED, ignoreCase = true)) {
                      setTextColor(Color.RED)

                  }
              }

          }
          criteriaAdapter = CriteriaListAdapter(data, this)
          recyclerView.adapter = criteriaAdapter

          //    Navigator.navigateToCriteriaListScreen(this, data)


          *//* val data_list = data.criteria?.get(0)
         for (x in 0 until data_list?.variable!!.size) {
             println(data_list.variable.toString())
             Log.e("Response", data_list.variable.toString())
         }

         for ((key, value) in data_list.variable) {
             Log.e("Response", "$key = $value")
             Log.e("Response", "${value.values}")

         }
         Toast.makeText(this, "variable values", Toast.LENGTH_LONG).show()

         Toast.makeText(this, "You clicked: ${data.name}", Toast.LENGTH_LONG).show()*//*
    }*/

    override fun onDestroy() {
        super.onDestroy()

//Clear all your disposables//

        myCompositeDisposable?.clear()

    }

    override fun onSuccess(objects: List<Response>) {

        try {
            responseDataList = ArrayList(objects)
            parentAdapter = ListAdapter(responseDataList!!, this)
            recyclerView.adapter = parentAdapter
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onError(objects: String?) {
        MyUtil.showToast(this, objects)
    }

}
