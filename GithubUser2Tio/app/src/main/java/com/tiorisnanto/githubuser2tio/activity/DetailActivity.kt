package com.tiorisnanto.githubuser2tio.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.tiorisnanto.githubuser2tio.R
import com.tiorisnanto.githubuser2tio.adapter.SectionPagerAdapter
import com.tiorisnanto.githubuser2tio.databinding.ActivityDetailBinding
import com.tiorisnanto.githubuser2tio.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        private val TITLE_TABS = intArrayOf(R.string.followers, R.string.following)
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Detail User")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        if (username != null) {
            detailViewModel.setDetailUser(username)
            detailViewModel.getDetailUser().observe(this) {
                binding.apply {
                    Glide.with(this@DetailActivity)
                        .load(it.avatarUrl)
                        .apply(
                            RequestOptions.circleCropTransform()
                                .placeholder(R.drawable.ic_launcher_background)
                        )
                        .into(imgItemPhoto)

                    tvName.text = it.name ?: getString(R.string.name)
                    tvUsername.text = it.username
                    tvLocation.text = it.location ?: "-"
                    tvCompany.text = it.company ?: "-"
                    tvRepository.text = it.publicRepos.toString()
                    tvFollowers.text = it.followers.toString()
                    tvFollowing.text = it.following.toString()
                }
            }
        }

        val sectionsPagerAdapter = SectionPagerAdapter(this, bundle)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
                tab.text = resources.getString(TITLE_TABS[position])
            }.attach()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}