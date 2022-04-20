import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.model.UserLeaveDetailJson
import com.ieltslearning.base.BaseAdapter
import kotlinx.android.synthetic.main.item_category.view.*

class RequestCategoryAdapter(val baseActivity: KotlinBaseActivity, val itemClick: (Int) -> Unit) :
    BaseAdapter<UserLeaveDetailJson.Data>(
        R.layout.item_category
    ) {

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.apply {
            tvcategory.setText(list[position].leave_category.category)
            categroycound.setText(list[position].available_leaves.toString())
            holder.itemView.setOnClickListener {
                itemClick(position)
            }
        }
    }
}