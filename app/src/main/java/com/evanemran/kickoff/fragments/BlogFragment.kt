package com.evanemran.kickoff.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.evanemran.kickoff.BlogDetailsActivity
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.*
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.BlogManager
import com.evanemran.kickoff.models.*
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager: BlogManager = BlogManager(requireContext())

        val animation: Sprite = Circle()
        spin_kit_blog.setIndeterminateDrawable(animation)

        manager.getBlogs(blogResponseListener)

    }

    private val blogClickListener: ClickListener<Blog> = object : ClickListener<Blog> {
        override fun onClicked(data: Blog) {
            startActivity(Intent(requireContext(), BlogDetailsActivity::class.java)
                .putExtra("url", data.url))
        }
    }

    private val blogResponseListener: ResponseListener<BlogResponse> = object : ResponseListener<BlogResponse> {
        override fun didFetch(message: String, response: BlogResponse) {
            recycler_blog.setHasFixedSize(true)
            recycler_blog.layoutManager = GridLayoutManager(requireContext(), 1)
            val adapter: BlogAdapter = BlogAdapter(requireContext(), response.articles, blogClickListener)
            recycler_blog.adapter = adapter
            spin_kit_blog.visibility = View.GONE
            recycler_blog.visibility = View.VISIBLE
        }

        override fun didError(message: String) {
            spin_kit_blog.visibility = View.GONE
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}