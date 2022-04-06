package com.sumit.employedetails.ui.countryPerfomance

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumit.employedetails.R
import com.sumit.employedetails.databinding.ItemEmployeeBinding
import com.sumit.employedetails.models.SalesZone

class SalesZoneAdapter(private val context: Context, private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<SalesZoneAdapter.ViewHolder>() {



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

        fun bind(salesRegion: SalesZone,onClickListener: OnClickListener) {
            with(binding) {
                tvRegions.text = salesRegion.territory.substringBefore(':')
                tvRegions.setOnClickListener {
                    onClickListener.onClick(salesRegion.territory)
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<SalesZone>() {
        override fun areItemsTheSame(oldItem: SalesZone, newItem: SalesZone): Boolean {
            return oldItem.territory == newItem.territory
        }

        override fun areContentsTheSame(oldItem: SalesZone, newItem: SalesZone): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<SalesZone>) = differ.submitList(list)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position],onClickListener)
    }
}