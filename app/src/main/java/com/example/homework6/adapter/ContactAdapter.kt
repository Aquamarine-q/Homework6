package com.example.homework6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework6.R
import com.example.homework6.model.Contact
import com.squareup.picasso.Picasso

/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Contact] data object.
 */
class ContactAdapter(
    private val dataset: List<Contact>
) : RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Contact object.
    class ItemViewHolder(view: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(view) {
        val fTextView: TextView = view.findViewById(R.id.first_name)
        val lTextView: TextView = view.findViewById(R.id.last_name)
        val nTextView: TextView = view.findViewById(R.id.number)
        val imageView: ImageView = view.findViewById(R.id.item_image)

        init {
            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout, mListener)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.fTextView.text = item.firstName
        holder.lTextView.text = item.lastName
        holder.nTextView.text = item.number

        try {
            Picasso.get().load(item.imageReference).into(holder.imageView)
        } catch (e: Exception) {

        } finally {
            // optional finally block
        }
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size
}