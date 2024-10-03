package ru.sevagrbnv.shambambukliapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val _cellList: MutableStateFlow<MutableList<Cell>> = MutableStateFlow(mutableListOf())
    val cellList: StateFlow<List<Cell>>
        get() = _cellList

    fun addCell() {
        val time = System.currentTimeMillis()
        val type = if (Random.nextInt() % 2 == 0) CellType.DEAD else CellType.LIFE

        val newList = _cellList.value.toMutableList()
        newList.add(Cell(id = time, type = type))
        _cellList.value = newList

        checkThreeDeadCells()
        checkThreeLifeCells()
    }

    private fun checkThreeLifeCells() {
        val list = _cellList.value
        if (list.size >= 3) {
            val lastCells = list.subList(list.lastIndex - 2, list.lastIndex + 1)

            if (lastCells.filter { it.type == CellType.LIFE }.size == 3) {
                val newList = _cellList.value.toMutableList()
                newList.add(Cell(id = System.currentTimeMillis(), type = CellType.BEING))
                _cellList.value = newList
            }
        }
    }

    private fun checkThreeDeadCells() {
        val list = _cellList.value
        if (list.size >= 4) {
            val lastCells = list.subList(list.lastIndex - 2, list.lastIndex + 1)

            if (lastCells.filter { it.type == CellType.DEAD }.size == 3
                && list[list.lastIndex - 3].type == CellType.BEING) {
                val newList = _cellList.value.toMutableList()
                newList.removeAt(list.lastIndex - 3)
                _cellList.value = newList
            }
        }
    }
}