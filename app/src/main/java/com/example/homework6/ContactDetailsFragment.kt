package com.example.homework6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.homework6.model.Contact
import com.squareup.picasso.Picasso

class ContactDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fTextView: TextView = view.findViewById(R.id.first_name)
        val lTextView: TextView = view.findViewById(R.id.last_name)
        val nTextView: TextView = view.findViewById(R.id.number)
        val imageView: ImageView = view.findViewById(R.id.item_image)

        val bundle = this.arguments
        if (bundle != null) {
            val contact = bundle.getParcelable<Contact>("contact")!!
            fTextView.text = contact.firstName
            lTextView.text = contact.lastName
            nTextView.text = contact.number

            try {
                Picasso.get().load(contact.imageReference).into(imageView)
            } catch (e: Exception) {

            } finally {
                // optional finally block
            }
        }
    }
}