package com.khilman.www.batiknusantaraencyclopedia

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import com.khilman.www.batiknusantaraencyclopedia.adapter.MainAdapter
import com.khilman.www.batiknusantaraencyclopedia.model.DataItem
import com.khilman.www.batiknusantaraencyclopedia.model.ResponseArticles
import com.khilman.www.listmovierecyclerview.network.InitRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpEverything()
        showArticles()
    }

    fun setUpEverything() {
        // Sembunyikan keyboard virtual
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        rvListEncyclopedia.isNestedScrollingEnabled = false
        rvListEncyclopedia.layoutManager = LinearLayoutManager(this)
        // Listener untuk mendeteksi perubahan Text di searchbox
        etSearchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val keyword = etSearchBox.text.toString()
                //TODO: Tampilkan Loading
                showLoading()
                //TODO: Ambil data dari API
                InitRetrofit().getInstance().requestSearchArticles(keyword).enqueue(object : Callback<ResponseArticles> {
                    override fun onFailure(call: Call<ResponseArticles>, t: Throwable) {
                        t.printStackTrace()
                        hideLoading()
                    }

                    override fun onResponse(call: Call<ResponseArticles>, response: Response<ResponseArticles>) {
                        if (response.isSuccessful) {
                            hideLoading()
                            if (response.body()?.status!!) {
                                //Pencarian di temukan, set adapter untuk Recycler View
                                rvListEncyclopedia.adapter = MainAdapter(this@MainActivity, response.body()?.data!!)
                            } else {
                                //Pencarian tidak ditemukan
                                showLoading()
                                toast("Pencarian tidak ditemukan")
                            }
                        }
                    }

                })
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    //Menampung data Artikel dari API
    private lateinit var dataArticles: List<DataItem?>

    fun showArticles() {
        //TODO: Tampilkan Loading
        showLoading()

        //TODO: Ambil data dari API
        InitRetrofit().getInstance().requestGetArticles().enqueue(object : Callback<ResponseArticles> {
            override fun onFailure(call: Call<ResponseArticles>, t: Throwable) {
                t.printStackTrace()
                hideLoading()
            }

            override fun onResponse(call: Call<ResponseArticles>, response: Response<ResponseArticles>) {
                if (response.isSuccessful) {
                    hideLoading()
                    if (response.body()?.status!!) {
                        dataArticles = response.body()?.data!!
                        rvListEncyclopedia.adapter = MainAdapter(this@MainActivity, dataArticles)
                    } else {
                        toast("Tidak ada artikel untuk ditampilkan!")
                    }
                }
            }
        })
    }

    fun showLoading() {
        progressBar.visibility = View.VISIBLE
        nsArticleContainer.visibility = View.GONE
    }

    fun hideLoading() {
        progressBar.visibility = View.GONE
        nsArticleContainer.visibility = View.VISIBLE
    }
}
