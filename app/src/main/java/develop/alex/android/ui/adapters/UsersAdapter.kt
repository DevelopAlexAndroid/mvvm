package develop.alex.android.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import develop.alex.android.R
import develop.alex.android.data.pojo.ListUserModel
import kotlinx.android.synthetic.main.item_user.view.*
import javax.inject.Inject

class UsersAdapter
@Inject constructor(
    private val listenerAdapter: ListenerAdapter
) : RecyclerView.Adapter<ViewHolder>() {

    var users: List<ListUserModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  Log.d("UsersAdapter", "Notify item $position")
        val user = users[position]
        holder.tv.text = user.loginval

        holder.item.setOnClickListener {
            listenerAdapter.itemClick(user.loginval)
        }
    }

    fun setData(newUsers: List<ListUserModel>) {
        this.users = newUsers
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tv: TextView = view.text_name
    val item: ConstraintLayout = view.item
}