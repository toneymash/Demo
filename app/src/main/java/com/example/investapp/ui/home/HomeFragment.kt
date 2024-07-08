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
            Pair(R.drawable.bbonds, "  Bonds emerged as crucial financial instruments in the debt markets. "+"Governments and corporations issued these fixed-income securities "+"to raise capital. Investors purchased bonds, which promised regular"+" interest payments and return of principal at maturity. The bonds'"+" creditworthiness was assessed by rating agencies, influencing their perceived risk and yield. "+"As interest rates fluctuated, bond prices adjusted inversely. Various types of bonds, including Treasury, municipal, and corporate, offered different risk-return profiles. Bonds played a vital role in investment portfolios, providing income and stability, especially for risk-averse investors. The secondary bond market facilitated trading, allowing investors to buy and sell bonds before maturity. "),
            Pair(R.drawable.reteireplan, "A retirement plan is a financial strategy to ensure financial security during one's later years. Key elements include starting early to benefit from compound interest, diversifying investments across stocks, bonds, and other assets, and regularly contributing to tax-advantaged accounts like 401(k)s or IRAs. It's important to assess your anticipated retirement lifestyle and expenses, factor in inflation, and consider potential healthcare costs. Social Security benefits can supplement savings but shouldn't be relied upon entirely. Periodically review and adjust your plan as your circumstances change. Consider consulting a financial advisor for personalized guidance."),
            Pair(R.drawable.mm, "The money market is a segment of the financial market dealing with short-term borrowing, lending, and trading of highly liquid financial instruments. These typically mature within one year and include Treasury bills, commercial paper, certificates of deposit, and repurchase agreements") ,
            Pair(R.drawable.mm, "The money market is a segment of the financial market dealing with short-term borrowing, lending, and trading of highly liquid financial instruments. These typically mature within one year and include Treasury bills, commercial paper, certificates of deposit, and repurchase agreements. Money market instruments are considered low-risk, low-yield investments, often used by institutions and individuals for cash management. The market plays a crucial role in maintaining liquidity in the financial system, providing short-term funding for banks, corporations, and governments. Money market funds are popular investment vehicles that allow retail investors to participate in this market, offering slightly higher returns than traditional savings accounts while maintaining high liquidity."),
            Pair(R.drawable.trainig, "A training program for an investment app is designed to equip users with the necessary skills and knowledge to effectively navigate and utilize the app's features for optimal financial decision-making. This program encompasses comprehensive modules that cover the basics of financial products, such as money markets, retirement plans, bonds, and real estate investments, as well as advanced techniques for portfolio management and risk assessment. Participants will benefit from a blend of interactive online tutorials, practical demonstrations, and real-time simulations that replicate market conditions")
        )

        val adapter = ViewPagerAdapter(imagesAndTexts)
        val viewPager = binding.viewPager2
        viewPager.adapter = adapter
    }
    }
