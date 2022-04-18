package com.example.opaynhrms.adapter


import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.FAQJson
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_notification.view.*

class FaqAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<FAQJson.Data>(
        R.layout.item_notification
    ) {
    var flag = false
    var date1 = ""
    var date1time = ""

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {

        holder.itemView.apply {
            ivdonwn.gone()
            tvans.visible()
            tvans.maxLines = 2
            tvtitle.setText(list[position].question)
            tvans.setText(list[position].answer)

            if (list[position].created_at.isNotNull()) {
                val firstDateFormate = list[position].created_at.split("T")
                date1 = firstDateFormate[0].toString()
                //[20-05-1954, 20-:05:16] - [0] get the 0 index
                date1time = firstDateFormate[1].split(".")[0]
                tvdate.setText(date1 + " : " + date1time)

            }

            cvContainer.setOnClickListener {
                if (!flag) {
                    ivdonwn.setImageResource(R.drawable.ic_up_arrow)
//                    tvans.visible()
                    view1.visible()
                    tvans.maxLines = 10
                    flag = true
                } else {
                    ivdonwn.setImageResource(R.drawable.ic_down_arrow)
                    view1.gone()
//                    tvans.gone()
                    tvans.maxLines = 2
                    flag = false
                }

            }

        }


    }

}