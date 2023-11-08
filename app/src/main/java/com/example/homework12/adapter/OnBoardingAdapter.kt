package com.example.homework12.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework12.Model.onBoarding
import com.example.homework12.databinding.ItemOnboardingBinding
import com.example.homework12.utils.loadImage

class OnBoardingAdapter(private val onClick:()->Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list= arrayListOf<onBoarding>(
        onBoarding("Title 1", "Desc 1", "https://cdn-icons-png.flaticon.com/512/2098/2098402.png"),
        onBoarding("Title 2", "Desc 2", "https://img.freepik.com/free-vector/male-athlete-bicycle-landscape_18591-77146.jpg"),
        onBoarding("Title 3", "Desc 3", "https://thumbs.dreamstime.com/z/scooter-courier-box-goods-delivery-man-respiratory-mask-online-service-home-vector-illustration-bicycle-180956202.jpg"),
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    }

    override fun getItemCount(): Int=list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])

    }
    inner class OnBoardingViewHolder(private val binding:ItemOnboardingBinding):
        ViewHolder(binding.root) {
            fun bind(onBoarding: onBoarding)= with(binding){
                tvTitle.text=onBoarding.title
                tvDesc.text=onBoarding.desc
                tvSkip.isVisible=adapterPosition!=list.lastIndex
                btnStart.isVisible=adapterPosition==list.lastIndex
                btnStart.setOnClickListener{
                    onClick()

                }
                tvSkip.setOnClickListener {
                    onClick()

                }
                ivBoard.loadImage(onBoarding.image.toString())
            }

        }
}