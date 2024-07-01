package com.example.investapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.investapp.R
import com.example.investapp.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        val viewPager: ViewPager2 = findViewById(R.id.view_pager2)


        val imagesAndTexts = listOf(
            Pair(R.drawable.image1, "government bonds this are  this that willl bbrrr this is for testing purporse not a bid deal mehn"),
            Pair(R.drawable.image2, "goodbye for Image 2"),
            Pair(R.drawable.image3, "Text for Image 3") ,
            Pair(R.drawable.image2, "goodbye for Image 2"),
            Pair(R.drawable.image3, "Text for Image 3")
        )

        val adapter = ViewPagerAdapter(imagesAndTexts)
        val viewPager = binding.viewPager2
        viewPager.adapter = adapter
    }
    }
