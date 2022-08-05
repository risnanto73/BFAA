package com.tiorisnanto.githubuser2tio.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tiorisnanto.githubuser2tio.adapter.ListUserAdapter
import com.tiorisnanto.githubuser2tio.databinding.ActivityMainBinding
import com.tiorisnanto.githubuser2tio.model.ResponseUser
import com.tiorisnanto.githubuser2tio.viewmodel.SearchViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ListUserAdapter
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        adapter = ListUserAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClick(data: ResponseUser) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.username)
                    startActivity(it)
                }
            }

        })

        searchViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SearchViewModel::class.java)
        searchViewModel.getSearchUser().observe(this) {
            if (it != null) {
                adapter.setListUser(it)
                showLoading(false)
                binding.imgGithub.visibility = View.GONE
                binding.tvInputUsername.visibility = View.GONE
            }
        }

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            tvInputUsername.visibility = View.VISIBLE
            imgGithub.visibility = View.VISIBLE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    searchViewModel.setSearchUser(query)
                    tvInputUsername.visibility = View.GONE
                    imgGithub.visibility = View.GONE
                    showLoading(true)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()) {
                        searchViewModel.setSearchUser(newText)
                        showLoading(false)
                        Toast.makeText(this@MainActivity, "User tidak ditemukan", Toast.LENGTH_SHORT).show()
                    } else {
                        searchViewModel.setSearchUser(newText)
                        showLoading(true)
                    }
                    return false
                }

            })
        }
    }

    private fun showLoading(state: Boolean) {
        binding.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}