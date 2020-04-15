package develop.alex.android.providers.adapters

import androidx.recyclerview.widget.DiffUtil
import develop.alex.android.data.pojo.ListUserModel

class DiffUtilCallback
constructor(
    private val oldList: List<ListUserModel>,
    private val newList: List<ListUserModel>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
            = oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
            = oldList[oldItemPosition] == newList[newItemPosition]

    //вернуть определенное поле для изменненого элемента
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}