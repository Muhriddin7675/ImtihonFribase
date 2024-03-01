package com.example.imtihonfribase.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imtihonfribase.data.ProductData
import com.example.imtihonfribase.databinding.ItemCategoryProductBinding

class AllProductAdapter : ListAdapter<ProductData, AllProductAdapter.AllProductViewHolder>(LibraryDiffUtil) {
    var time = System.currentTimeMillis()
    lateinit var clickProductItem:(ProductData)->Unit

    object LibraryDiffUtil : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean =
            oldItem.docId == newItem.docId

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean =
            oldItem == newItem
    }

    inner class AllProductViewHolder(private val binding: ItemCategoryProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            binding.root.setOnClickListener{
                if(System.currentTimeMillis() - time > 500){
                    clickProductItem.invoke(getItem(adapterPosition))
                }
                time = System.currentTimeMillis()
            }

        }
        @SuppressLint("SetTextI18n")
        fun bind(data: ProductData) {

         binding.productName.text = data.typeName
            binding.productSum.text = data.price + " so'm"
            val sumString = data.price.replace(" ","")
            val sum = sumString.toInt() / 12 * 1.0
            binding.textSumOy.text = "$sum so'm/oyiga"

            Glide.with(binding.root)
                .load(data.image)
                .into(binding.image)

            binding.productSolid.text = "100 ta buyurtma"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductViewHolder =
        AllProductViewHolder(
            ItemCategoryProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AllProductViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun  setOnClickProductItem(block:(ProductData) ->Unit){
        clickProductItem = block
    }

}