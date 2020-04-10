package develop.alex.android.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import develop.alex.android.R
import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.providers.DiffUtilCallback
import kotlinx.android.synthetic.main.item_user.view.*
import javax.inject.Inject

class UsersAdapter
@Inject constructor(
    private val itemClick: ItemClick
) : RecyclerView.Adapter<ViewHolder>() {

    private var users: List<ListUserModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.tv.text = user.loginval

        holder.tv.setOnClickListener {
            itemClick.click(position)
        }
    }

    fun setData(newUsers: List<ListUserModel>) {
        (users as ArrayList).clear()
        this.users = newUsers
    }

}
class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tv: TextView = view.text_name
}