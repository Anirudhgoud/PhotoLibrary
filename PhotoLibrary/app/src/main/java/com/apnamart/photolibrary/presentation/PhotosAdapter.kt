package com.apnamart.photolibrary.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apnamart.photolibrary.R
import com.apnamart.photolibrary.domain.model.PhotosApiResponse
import com.bumptech.glide.Glide

class PhotosAdapter :
    RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private val photosList: ArrayList<PhotosApiResponse> = arrayListOf()

    override fun getItemCount(): Int {
        return photosList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        photosList[position].also { photo ->
            photo.downloadUrl?.let { imageUrl ->
                Glide.with(holder.itemView.context)
                    .load(imageUrl)
                    .disallowHardwareConfig()
                    .into(holder.ivAuthor)
                holder.tvAuthorName.text = photo.author
            }
        }
    }

    fun submitUpdate(photos: List<PhotosApiResponse>) {
        photosList.clear()
        photosList.addAll(photos)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAuthorName: TextView = view.findViewById(R.id.text_author_name)
        val ivAuthor: ImageView = view.findViewById(R.id.image_author)
    }
}