package ru.sevagrbnv.shambambukliapp

data class Cell(
    val id: Long,
    val type: CellType
)

enum class CellType{
    DEAD, LIFE, BEING
}