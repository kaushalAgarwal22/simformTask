package com.example.simformtask

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simformtask.adapter.UserAdapter
import com.example.simformtask.model.Result
import com.example.simformtask.model.UserData
import com.example.simformtask.network.Common
import com.example.simformtask.network.UserService
import com.example.weatherapp.database.User
import com.example.weatherapp.database.UserDatabase
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retServce: UserService
    private lateinit var adapter: UserAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        linearLayoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = linearLayoutManager
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        adapter = UserAdapter(this)
        recyclerView.setAdapter(adapter)

        loadOfflineUsersData(adapter)
        insertAndFetchUsers(adapter)
    }

    fun loadOfflineUsersData(adapter: UserAdapter) {

        object : AsyncTask<Void, Void, Int>() {
            override fun doInBackground(vararg p0: Void?): Int {
                return UserDatabase.getInstance(this@MainActivity).userDao.getCount()
            }

            override fun onPostExecute(result: Int) {
                if (result > 0)
                    loadUsers(adapter)
            }

        }.execute()

    }

    fun insertAndFetchUsers(adapter: UserAdapter) {

        if (!Common.hasNetworkAvailable(this))
            return

        retServce = Common.userService

        retServce.getUsers().enqueue(object : retrofit2.Callback<UserData> {

            override fun onFailure(call: Call<UserData>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                var userData = response.body()
                if (userData != null && userData.results != null && !userData.results.isEmpty()) {
                    inserUsers(userData.results, adapter)
                }
            }
        })
    }

    fun inserUsers(results: List<Result>, adapter: UserAdapter) {

        var users = ArrayList<User>()

        for (result in results) {

            var user = User()

            user.gender = result.gender
            user.name = result.name.title + " " + result.name.first + " " + result.name.last
            user.dob = result.dob.date
            user.email = result.email
            user.phone = result.phone
            user.cell = result.cell
            user.image = result.picture.thumbnail
            user.address =
                result.location.street.number.toString() + " " + result.location.street.name + ", " + result.location.city + ", " + result.location.state

            users.add(user)
        }

        object : AsyncTask<Void, Void, Int>() {
            override fun doInBackground(vararg p0: Void?): Int {
                UserDatabase.getInstance(this@MainActivity).userDao.deleteAll()
                UserDatabase.getInstance(this@MainActivity).userDao.insertAll(users)
                return users.size
            }

            override fun onPostExecute(result: Int?) {
                loadUsers(adapter)
            }

        }.execute()

    }

    fun loadUsers(adapter: UserAdapter) {

        object : AsyncTask<Void, Void, List<User>>() {

            override fun doInBackground(vararg p0: Void?): List<User> {
                return UserDatabase.getInstance(this@MainActivity).userDao.getAll()
            }

            override fun onPostExecute(result: List<User>) {
                adapter.clearList()
                adapter.addUsers(result)
                adapter.notifyDataSetChanged()
            }

        }.execute()
    }
}