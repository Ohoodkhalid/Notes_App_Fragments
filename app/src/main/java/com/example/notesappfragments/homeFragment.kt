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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class homeFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var addNoteEt: EditText
    lateinit var addBu : Button
    lateinit var recView: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter
    private lateinit var notes: List<Note>
    lateinit var viewModel:MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.noteSP), Context.MODE_PRIVATE)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        notes = arrayListOf()
            viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
            viewModel.getNotes().observe(viewLifecycleOwner, {
                notes -> rvAdapter.update(notes)
            })

            addNoteEt = view.findViewById(R.id.addNoteEt)
            addBu = view.findViewById(R.id.addBu)


            addBu.setOnClickListener {
                viewModel.addNote(addNoteEt.text.toString())
            }
            recView = view.findViewById(R.id.recView)
            rvAdapter = RecyclerViewAdapter(this)
            recView.adapter = rvAdapter
            recView.layoutManager = LinearLayoutManager(requireContext())
            viewModel.getNotes()



        return view

    }


}