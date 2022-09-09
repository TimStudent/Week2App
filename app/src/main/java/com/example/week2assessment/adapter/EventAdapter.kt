package com.example.week2assessment.adapter
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.week2assessment.R
import com.example.week2assessment.databinding.CardviewBinding
import com.example.week2assessment.model.EventData

class EventAdapter(
    private val mList: MutableList<EventData> = mutableListOf(),
    private val onEventClickListener: (EventData) -> Unit):RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        CardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    fun update(newData: List<EventData>){
        mList.clear()
        mList.addAll(newData)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(binding: ViewHolder, position: Int) {
        binding.bind(mList[position], onEventClickListener)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}

class ViewHolder(private val binding: CardviewBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(event:EventData, onEventClickListener: (EventData) -> Unit){
        binding.titleView.text = event.title
        binding.categoryView.text = event.category
        binding.descriptionView.text = event.description
        binding.dateView.text = event.date
        binding.CardViewLayout.setOnClickListener {
            Navigation.createNavigateOnClickListener(
                R.id.action_MainFragment_to_DetailsFragment)
                .onClick(binding.CardViewLayout)
            onEventClickListener.invoke(event)
        }
    }
}