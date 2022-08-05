package com.tiorisnanto.githubusertio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tiorisnanto.githubusertio.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail_user)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setTitle("Detail User")

        val dataUser = intent.getParcelableExtra<Users>(EXTRA_USER)
        if (dataUser != null) {
            binding.imgItemPhoto.setImageResource(dataUser.avatar)
            binding.tvItemName.text = dataUser.name
            binding.tvItemUsername.text = dataUser.username
            binding.tvItemCompany.text = "Company : ${dataUser.company}"
            binding.tvItemLocation.text = "Location : ${dataUser.location}"
            binding.tvItemRepository.text = "Repository : ${dataUser.repository}"
            binding.tvItemFollowers.text = "Followers : ${dataUser.followers}"
            binding.tvItemFollowing.text = "Following : ${dataUser.following}"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}