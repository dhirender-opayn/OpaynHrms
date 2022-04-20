package com.example.opaynhrms.adapter

 import android.util.Log
 import androidx.core.widget.addTextChangedListener
 import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.extensions.gone
 import com.example.opaynhrms.model.CommonModelCategoryList
 import com.example.opaynhrms.model.LeaveCategoryJson
 import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_category_lables.view.*


class CategoryLabelAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<CommonModelCategoryList>(
        R.layout.item_category_lables
    ) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            if (position == list.lastIndex){
                clCategory.gone()
            }
           Log.e("d515515",position.toString())
            tvcaseueleave.setText(list[position].name)
            caseualcount.setText(list[position].total_leave)


            if (list[position].flag){
                caseualcount.isEnabled = false
                caseualcount.setBackgroundColor(baseActivity.getColor(R.color.light_gre1))
                if (list[position].name.equals("Short leave")  ){
                    caseualcount.isEnabled = true
                    caseualcount.background = baseActivity.getDrawable( R.drawable.rectangle_black_border)

                }
                if (list[position].name.equals("Earned leave")){
                    caseualcount.background = baseActivity.getDrawable( R.drawable.rectangle_black_border)
                    caseualcount.isEnabled = true
                }

            }


            caseualcount.addTextChangedListener {
                if (!it.toString().isEmpty()){
                    list[position].count = it.toString()
                }

            }
        }
    }
}