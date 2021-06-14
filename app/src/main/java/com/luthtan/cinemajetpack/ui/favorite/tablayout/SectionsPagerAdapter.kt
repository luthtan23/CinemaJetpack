package com.luthtan.cinemajetpack.ui.favorite.tablayout

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.ui.favorite.tablayout.movie_favorite.MovieFavoriteFragment
import com.luthtan.cinemajetpack.ui.favorite.tablayout.tvshow_favorite.TvShowFavoriteFragment

class SectionsPagerAdapter(private val context: Context, private val fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tvshow)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFavoriteFragment()
            1 -> TvShowFavoriteFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        context.resources.getString(TAB_TITLES[position])
}