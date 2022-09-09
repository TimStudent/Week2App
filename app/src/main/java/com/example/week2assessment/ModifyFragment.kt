package com.example.week2assessment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.week2assessment.DataHelper.DBHelper
import com.example.week2assessment.databinding.FragmentModifyBinding

class ModifyFragment : Fragment() {

    private val model: SharedViewModel by activityViewModels()
    private val binding by lazy {
        FragmentModifyBinding.inflate(layoutInflater)
    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.modifyBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_ModifyEventFragment_to_MainFragment)
        }
        binding.ModifyTitle.setText(model.importantTitle)
        binding.ModifyCategory.setText(model.importantCategory)
        binding.ModifyDescription.setText(model.importantDescription)
        binding.ModifyDate.setText(model.importantDate)

        val db = activity?.let { it -> DBHelper(it, null) }
        val contentValues = ContentValues()
        val cursor = db?.getName()
        cursor?.move(model.importantPosition.toInt())

        binding.modifySaveBtn.setOnClickListener {
            if (binding.ModifyTitle.text.toString().isEmpty() ||
                binding.ModifyCategory.text.toString().isEmpty() ||
                binding.ModifyDescription.text.toString().isEmpty() ||
                binding.ModifyDate.text.toString().isEmpty()
            ) {
            //
            } else {
                contentValues.put(DBHelper.VAL1, binding.ModifyTitle.text.toString())
                contentValues.put(DBHelper.VAL2, binding.ModifyCategory.text.toString())
                contentValues.put(DBHelper.VAL3, binding.ModifyDescription.text.toString())
                contentValues.put(DBHelper.VAL4, binding.ModifyDate.text.toString())
                if (cursor?.getInt(cursor.getColumnIndex(DBHelper.KEY))==model.importantPosition.toInt()){
                    db.writableDatabase?.update(DBHelper.TABLE_NAME, contentValues,
                        DBHelper.KEY + "=" + cursor.getInt(cursor.getColumnIndex(DBHelper.KEY)).toString(),
                        null)
                    db.close()
                    findNavController().navigate(R.id.action_ModifyEventFragment_to_MainFragment)
                }
            }
        }
        return binding.root
    }

}