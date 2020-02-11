package com.test.lbcpt.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.lbcpt.R
import com.test.lbcpt.model.Album


/**
 * Provide views to RecyclerView with data from dataSet.
 *
 * Initialize the dataset of the Adapter.
 *
 * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
 */

/*
class AlbumAdapter(val Albums: List<Album>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
 */
class AlbumAdapter internal constructor(
    context: Context, val itemClickListener: View.OnClickListener
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(),View.OnClickListener {

    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var albums = emptyList<Album>() // Cached copy of Albums

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.card_view)
        val titleItemView: TextView = cardView.findViewById(R.id.title)
        val thumbnail: ImageView = cardView.findViewById(R.id.thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView = inflater.inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val current = albums[position]
        holder.titleItemView.text = current.title
        Picasso.get()
            .load(current.thumbnailUrl)
            .fit()
            .into(holder.thumbnail)
        holder.cardView.tag = position
        holder.cardView.setOnClickListener(itemClickListener)
    }

    internal fun setAlbum(albums: List<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }
    override fun getItemCount() = albums.size
}



