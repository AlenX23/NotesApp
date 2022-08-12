package com.example.notesapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.model.User
import com.example.notesapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.text.DateFormat
import java.util.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val title = add_title.text.toString()
        val description = add_description.text.toString()

        if (inputCheck(title, description)) {
            val user = User(0, title, description, DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().time))

            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Note added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill in all the fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }
}
