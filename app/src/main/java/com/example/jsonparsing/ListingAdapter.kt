package com.example.jsonparsing

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.jsonparsing.databinding.LayoutItemViewBinding

class ListingAdapter() : RecyclerView.Adapter<ListingAdapter.ListingVIewHolder>() {

    var subCategoryList : List<ApiResponse.SubCategory> = mutableListOf()
    var onItemClick : ((ApiResponse.SubCategory) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ListingAdapter.ListingVIewHolder {
        val view = LayoutItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListingVIewHolder(view)
    }

    override fun onBindViewHolder(holder: ListingAdapter.ListingVIewHolder, position: Int) {
        val currentItem = subCategoryList[position]
        with(holder){
            with(subCategoryList[position]){
                binding.rating.rating = currentItem.star.toString().toFloat()
                binding.imageview
                binding.nameTv.text = currentItem.name.toString()
                binding.ratingCountTv.text = currentItem.installedRange.toString()

                Glide.with(itemView.context)
                    .load(currentItem.icon.toString())
                    .transform(CenterInside(), RoundedCorners(20))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imageview)

                binding.btnDownload.setOnClickListener {
                    onItemClick?.invoke(currentItem)
                }
            }
        }
    }

    override fun getItemCount(): Int = subCategoryList.size

    inner class ListingVIewHolder(val binding : LayoutItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateist(list : List<ApiResponse.SubCategory>){
        subCategoryList = list?: emptyList()
        notifyDataSetChanged()
    }

}