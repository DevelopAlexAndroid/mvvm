package develop.alex.android.providers

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.providers.adapters.DiffUtilCallback
import develop.alex.android.ui.adapters.UsersAdapter

fun <K, V> ViewModel.lazyMap(initializer: (K) -> V): Map<K, V> {
    val map = mutableMapOf<K, V>()
    return map.withDefault { key ->
        val newValue = initializer(key)
        map[key] = newValue
        return@withDefault newValue
    }
}

//Заглушка для обновления списка
fun Fragment.replaceAdapterList(oldList: List<ListUserModel>): ArrayList<ListUserModel> {
    val newList = ArrayList<ListUserModel>()
    newList.add(oldList[0])
    newList.add(oldList[1])
    newList.add(oldList[4])
    newList.add(oldList[5])
    newList.add(oldList[2])
    newList.add(oldList[3])
    newList.add(oldList[6])
    newList.add(oldList[8])
    newList.add(oldList[7])
    return newList
}

fun Fragment.applyDiffAdapter(
    adapter: UsersAdapter,
    oldList: List<ListUserModel>,
    newList: List<ListUserModel>
) {
    val diff = DiffUtil.calculateDiff(DiffUtilCallback(oldList, newList))
    adapter.setData(newList)
    diff.dispatchUpdatesTo(adapter)
}