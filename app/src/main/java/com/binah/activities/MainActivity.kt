package com.binah.activities

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.binah.R
import com.binah.adapters.OnItemClickListener
import com.binah.adapters.QuestionsRecyclerViewAdapter
import com.binah.api.ApiClient
import com.binah.data.DefaultData.Companion.PAGE_URL
import com.binah.data.ObjectSingleQuestion
import com.binah.data.ObjectsQueryResult
import com.binah.data.Params
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private lateinit var compositeDisposable: CompositeDisposable
private lateinit var recyclerView: RecyclerView
private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
private lateinit var radioGroup: RadioGroup
private var adapterList: ArrayList<ObjectSingleQuestion> = ArrayList()
private var fixedList: ArrayList<ObjectSingleQuestion> = ArrayList()
private const val INSTANCE_KEY = "OBJECTS"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioFilterGroup)
        recyclerView = findViewById(R.id.recyclerViewList)
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh)
        compositeDisposable = CompositeDisposable()


        //Swipe layout for refreshing
        mSwipeRefreshLayout.setOnRefreshListener {
            loadData()
        }

        recyclerView.adapter =
            QuestionsRecyclerViewAdapter(adapterList, object : OnItemClickListener {
                override fun onItemClicked(item: ObjectSingleQuestion) {

                    val intent = Intent(this@MainActivity, WebViewActivity::class.java)
                    intent.putExtra(PAGE_URL, item.link)
                    startActivity(intent)
                }
            })

        //Radiobutton filter
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)

            when (radio.id) {
                R.id.radioButtonAll -> updateAdapter(fixedList)
                R.id.radioButtonAnswered -> updateAdapter(fixedList.filter { it.is_answered })
                R.id.radioButtonUnanswered -> updateAdapter(fixedList.filter { !it.is_answered })

            }
        }

        //Checking saved instance state for screen rotation
        if (savedInstanceState != null) {
            fixedList =
                savedInstanceState.getSerializable(INSTANCE_KEY) as ArrayList<ObjectSingleQuestion>
            updateAdapter(fixedList)
        } else {
            loadData()
        }


    }

    //Recycler view adapter updater
    private fun updateAdapter(questions: List<ObjectSingleQuestion>) {
        adapterList.clear()
        adapterList.addAll(questions)
        recyclerView.adapter!!.notifyDataSetChanged()
        mSwipeRefreshLayout.isRefreshing = false


    }

    //Load data from API
    private fun loadData() {
        compositeDisposable.add(
            ApiClient.getClient.getQuestions(Params.getParamsSearch())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        )
    }



    //Response handler for API
    private fun handleResponse(objectsQueryResult: ObjectsQueryResult) {
        fixedList.clear()
        fixedList.addAll(objectsQueryResult.items)
        updateAdapter(fixedList)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    //Saving Instance
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(INSTANCE_KEY, fixedList)
    }


}