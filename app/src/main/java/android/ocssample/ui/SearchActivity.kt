package android.ocssample.ui

import android.app.Activity
import android.ocssample.R
import android.ocssample.ui.adapters.ContentAdapter
import android.ocssample.viewModels.SearchResultViewModel
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {

    lateinit var searchResultViewModel: SearchResultViewModel
    private val contentsAdapter by lazy { ContentAdapter(this as Activity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        RV_search_content.layoutManager = GridLayoutManager(this, 2)

        searchResultViewModel = ViewModelProviders.of(this).get(SearchResultViewModel::class.java)
        search_content_button.setOnClickListener() {
            searchResultViewModel.onClick(this, search_ET)
            RV_search_content.adapter = contentsAdapter
        }
        updateDisplay()
    }


    /**
     * update UI RecyclerView on contents refresh livedata
     */
    private fun updateDisplay() {
        searchResultViewModel.contents.observe(this, Observer { response ->
            Log.i(this.localClassName, "contents gathered=$response")
            response?.let {
                if (it.count > 0) {
                    handleRvVisibility(true)
                    contentsAdapter.contents = it.contents
                } else {
                    handleRvVisibility(false)
                }
            } ?: run {
                handleRvVisibility(false)
            }
        })
    }

    private fun handleRvVisibility(show: Boolean) {
        RV_search_content.visibility = if (show) View.VISIBLE else View.INVISIBLE
        empty_search_content.visibility = if (show) View.INVISIBLE else View.VISIBLE
    }
}