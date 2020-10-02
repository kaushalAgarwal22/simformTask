package com.example.simformtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simformtask.R
import com.example.simformtask.viewholder.UserViewholder
import com.example.weatherapp.database.User
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UserAdapter(private val context: Context) :
    RecyclerView.Adapter<UserViewholder>() {

    private var users: MutableList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewholder {

        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.inflate_item, parent, false)
        return UserViewholder(itemView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewholder, position: Int) {


        holder.name.text = users.get(position).name
        holder.email.text = users.get(position).email
        holder.cellNo.text = users.get(position).cell
        holder.phoneNo.text = users.get(position).phone
        holder.address.text = users.get(position).address
        holder.gender.text = users.get(position).gender
        holder.dob.text = users.get(position).dob?.split("T")?.get(0) ?: ""


        Picasso.get()
            .load(users.get(position).image)
            .into(holder.source_image)
    }

    fun clearList() {
       this.users.clear()
    }

    fun addUsers(users : List<User>){
       this.users.addAll(users)
    }

//    fun getUser() : User {
//        var user = User()
//
//        user.gender = "male"
//        user.name = "sonam"
//        user.dob = "30-7-91"
//
//        return user
//    }


}