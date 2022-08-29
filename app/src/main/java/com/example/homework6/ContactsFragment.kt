package com.example.homework6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework6.adapter.ContactAdapter
import com.example.homework6.data.Datasource

class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize data.
        val myDataset = Datasource().loadContacts()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = ContactAdapter(myDataset)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : ContactAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putParcelable("contact", myDataset[position])

                val contactDetailsFragment = ContactDetailsFragment()
                contactDetailsFragment.arguments = bundle
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        contactDetailsFragment,
                        "contactDetailsFragment"
                    )
                    .addToBackStack(null)
                    .commit()
            }

        })

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }
}