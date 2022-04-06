package com.sumit.employedetails.ui.areaPerfomance

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumit.employedetails.R
import com.sumit.employedetails.databinding.ItemEmployeeBinding
import com.sumit.employedetails.models.SalesArea
import com.sumit.employedetails.models.SalesZone

class SalesAreaAdapter(private val context: Context) :
    RecyclerView.Adapter<SalesAreaAdapter.ViewHolder>() {

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

        fun bind(salesRegion: SalesArea) {
            with(binding) {
                tvRegions.text = salesRegion.area

            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<SalesArea>() {
        override fun areItemsTheSame(oldItem: SalesArea, newItem: SalesArea): Boolean {
            return oldItem.area == newItem.area
        }

        override fun areContentsTheSame(oldItem: SalesArea, newItem: SalesArea): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<SalesArea>) = differ.submitList(list)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}