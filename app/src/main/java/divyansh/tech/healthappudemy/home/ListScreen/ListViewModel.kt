package divyansh.tech.healthappudemy.home.ListScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import divyansh.tech.healthappudemy.R
import divyansh.tech.healthappudemy.home.ListScreen.model.CarbType
import divyansh.tech.healthappudemy.home.ListScreen.model.ListItem

class ListViewModel: ViewModel() {

    private val _listOfItems = mutableStateListOf<ListItem>()
    val items: MutableList<ListItem> = _listOfItems

    init {
        _listOfItems.add(
            ListItem(
                id = 1,
                painter = R.drawable.image1,
                carbType = CarbType.VERY_LOW,
                text = "Salad with wheat and white egg",
                cals = "100 cals"
            )
        )
        _listOfItems.add(
            ListItem(
                id = 2,
                painter = R.drawable.image2,
                carbType = CarbType.LOW,
                text = "Bacons and cheesecake",
                cals = "600 cals"
            )
        )
        _listOfItems.add(
            ListItem(
                id = 3,
                painter = R.drawable.image3,
                carbType = CarbType.MED,
                text = "Mushroom Pizza",
                cals = "1000 cals"
            )
        )
        _listOfItems.add(
            ListItem(
                id = 5,
                painter = R.drawable.image4,
                carbType = CarbType.HIGH,
                text = "Pastries with extra cherries on top",
                cals = "1400 cals"
            )
        )
        _listOfItems.add(
            ListItem(
                id = 4,
                painter = R.drawable.image5,
                carbType = CarbType.VERY_HIGH,
                text = "Mushroom spaghetti with extra pizza sauce and olives",
                cals = "2000 cals"
            )
        )
    }

    fun deleteItem(item: ListItem) {
        _listOfItems.remove(item)
    }
}