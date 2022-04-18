package com.example.opaynhrms.adapter

 import androidx.core.widget.addTextChangedListener
 import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.extensions.gone
 import com.example.opaynhrms.model.LeaveCategoryJson
 import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_category_lables.view.*


class CategoryLabelAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<LeaveCategoryJson.Data>(
        R.layout.item_category_lables
    ) {


    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            if (position == list.lastIndex){
                clCategory.gone()
            }
            tvcaseueleave.setText(list[position].category)
            caseualcount.addTextChangedListener {
                if (!it.toString().isEmpty()){
                    list[position].count = it.toString()
                    itemClick(position)
                }
            }

        }
    }



}