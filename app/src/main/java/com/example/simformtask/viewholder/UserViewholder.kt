package com.example.simformtask.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.inflate_item.view.*

class UserViewholder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var name = itemView.textViewName
    var email = itemView.textViewEmail
    var gender = itemView.textViewGender
    var dob = itemView.textViewDob
    var phoneNo = itemView.textViewPhone
    var cellNo = itemView.textViewCell
    var address = itemView.textViewAddress


    var source_image = itemView.profile_image

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
    }

}