package com.example.notesapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.model.User
import com.example.notesapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.text.DateFormat
import java.util.*

class UpdateFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        view.update_title.setText(args.currentUser.title)
        view.update_description.setText(args.currentUser.description)

        view.update_btn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val title = update_title.text.toString()
        val description = update_description.text.toString()

        if(inputCheck(title, description)){

            val updateUser = User(args.currentUser.id, title, description, DateFormat.getDateInstance(
                DateFormat.FULL).format(Calendar.getInstance().time))

            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.title}",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${args.currentUser.title}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.title}?")
        builder.show()
    }
}