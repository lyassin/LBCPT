package com.test.lbcpt.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.lbcpt.R
import com.test.lbcpt.viewmodel.AlbumViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import com.test.lbcpt.MyApplication
import com.test.lbcpt.viewmodel.AlbumsViewModelProvider


class MainActivity : AppCompatActivity() , View.OnClickListener{
    override fun onClick(view: View) {
        if (view.tag != null) {
            var value = view.tag as Int
            //to open img Url
        }
    }

    private val albumViewModel by lazy { ViewModelProviders.of(this, AlbumsViewModelProvider())[AlbumViewModel::class.java] }
   // var albums = albumViewModel.albums

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = AlbumAdapter(this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))

        // Add an observer on the LiveData.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        albumViewModel.albums.observe(this, Observer { allalbums ->
            // Update the cached copy of the Albums in the adapter.
            allalbums?.let { adapter.setAlbum(it) }
        })

    }
}