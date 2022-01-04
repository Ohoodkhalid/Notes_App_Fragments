package com.example.notesappfragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [updateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class updateFragment : Fragment() {
    lateinit var viewModel:MyViewModel
    private lateinit var sharedPreferences: SharedPreferences

    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val  view = inflater.inflate(R.layout.fragment_update, container, false)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.noteSP), Context.MODE_PRIVATE)
        val NoteET = view.findViewById<EditText>(R.id.NoteET)
        val updateBU = view.findViewById<Button>(R.id.updateBU)
        updateBU.setOnClickListener{
            val noteId = sharedPreferences.getString("NoteId", "").toString()
            viewModel.editNote(noteId.toInt(), NoteET.text.toString())
            with(sharedPreferences.edit()) {
                putString("NoteId", NoteET.text.toString())
                apply()
            }
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }

        return view
    }


}