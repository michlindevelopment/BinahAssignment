package com.binah.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binah.R
import com.binah.data.DefaultData.Companion.DELIMITER
import com.binah.data.ObjectSingleQuestion
import com.squareup.picasso.Picasso
import org.apache.commons.text.StringEscapeUtils
import java.util.stream.Collectors


class QuestionsRecyclerViewAdapter(
    private val values: ArrayList<ObjectSingleQuestion>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<QuestionsRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position, itemClickListener)
        val item = values[position]

        holder.title.text = StringEscapeUtils.unescapeHtml4(item.title)
        holder.owner.text = item.owner.display_name
        holder.reputation.text = context.getString(R.string.reputation, item.owner.reputation)
        holder.tags.text = context.getString(R.string.tags, item.tags.stream().collect(Collectors.joining(DELIMITER)))
        Picasso.with(context).load(item.owner.profile_image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.textViewHeadline)
        val owner: TextView = view.findViewById(R.id.textViewOwner)
        val reputation: TextView = view.findViewById(R.id.textViewReputation)
        val tags: TextView = view.findViewById(R.id.textViewTags)
        val imageView: ImageView = view.findViewById(R.id.imageViewUserIcon)


        fun bind(position: Int, clickListener: OnItemClickListener) {
            itemView.setOnClickListener {
                clickListener.onItemClicked(values[position])
            }
        }
    }
}

//Interface for row click
interface OnItemClickListener {
    fun onItemClicked(item: ObjectSingleQuestion)
}


