package sudoku.utils

import java.awt.TextField
import javax.swing.JTextField

object Utils {
    fun mapTextFieldsToInt(textFields: Array<JTextField>): Array<Int> {
        return textFields.map { it.text }.map { if (it.trim() == "") return@map 0; return@map it.toInt() }.toTypedArray()
    }
}