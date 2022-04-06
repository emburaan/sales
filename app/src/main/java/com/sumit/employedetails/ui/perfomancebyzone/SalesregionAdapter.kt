package com.sumit.employedetails.ui.perfomancebyzone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumit.employedetails.R
import com.sumit.employedetails.databinding.ItemEmployeeBinding
import com.sumit.employedetails.models.SalesRegion

class SalesregionAdapter(private val context: Context, private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<SalesregionAdapter.ViewHolder>() {



    interface OnClickListener {
        fun onClick(performance:String)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false)
        )
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemEmployeeBinding.bind(view)

        fun bind(salesRegion: SalesRegion,onClickListener: OnClickListener) {
            with(binding) {
                tvRegions.text = salesRegion.region.substringBefore(':')
                tvRegions.setOnClickListener {
                    onClickListener.onClick(salesRegion.region)
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<SalesRegion>() {
        override fun areItemsTheSame(oldItem: SalesRegion, newItem: SalesRegion): Boolean {
            return oldItem.region == newItem.region
        }

        override fun areContentsTheSame(oldItem: SalesRegion, newItem: SalesRegion): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<SalesRegion>) = differ.submitList(list)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position],onClickListener)
    }
}