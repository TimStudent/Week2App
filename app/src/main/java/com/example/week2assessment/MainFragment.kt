package com.example.week2assessment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2assessment.DataHelper.DBHelper
import com.example.week2assessment.adapter.EventAdapter
import com.example.week2assessment.databinding.FragmentMainBinding
import com.example.week2assessment.model.EventData

class MainFragment : Fragment() {

    private val model: SharedViewModel by activityViewModels()
    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        EventAdapter {
            model.update(it.title, it.category, it.description, it.date)
            model.importantPosition = it.key
            Log.d(TAG, it.key)
        }
    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recyclerView = binding.eventRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)

        val data = ArrayList<EventData>()
        val db = activity?.let { it1 -> DBHelper(it1, null) }
        try {
            val cursor = db?.getName()
            if(cursor!=null){
                cursor.moveToFirst()
                data.add(
                    EventData(
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.VAL1)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.VAL2)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.VAL3)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.VAL4))
                    )
                )
                while(cursor.moveToNext()){
                    data.add(
                        EventData(
                            cursor.getString(cursor.getColumnIndex(DBHelper.KEY)),
                            cursor.getString(cursor.getColumnIndex(DBHelper.VAL1)),
                            cursor.getString(cursor.getColumnIndex(DBHelper.VAL2)),
                            cursor.getString(cursor.getColumnIndex(DBHelper.VAL3)),
                            cursor.getString(cursor.getColumnIndex(DBHelper.VAL4))
                        )
                    )
                }
            }
            cursor?.close()
            db?.close()
        }
        catch (exception:Exception) {

        }
        recyclerView.adapter = adapter
        adapter.update(data)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        binding.createEventBtn.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_FragmentOne)
        }
        return binding.root
    }
}
