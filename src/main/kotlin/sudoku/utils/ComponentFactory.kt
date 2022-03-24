package sudoku.utils

import sudoku.model.Sudoku
import sudoku.solver.SudokuSolver
import java.awt.GridLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JPanel
import javax.swing.JTextField

object ComponentFactory {
    private fun createInputFields(values: Array<Int> = Array(81) { 0 }): Array<JTextField> {
        val inputFields = Array(81) { JTextField() }
        for (i in 0..80) {
            val input = inputFields[i]

            input.text = values[i].toString().let { if (it.trim() == "0") "" else it }

            input.horizontalAlignment = JTextField.CENTER

            input.addKeyListener(object : KeyAdapter() {
                override fun keyTyped(e: KeyEvent) {
                    val c = e.keyChar

                    if (!Character.isDigit(c)) {
                        e.consume()
                        return
                    }

                    if (input.text.isNotEmpty()) {
                        e.consume()
                        return
                    }

                    val num = c.toString().let { if (it == "") return@let "0"; return@let it }.toInt()

                    val tempArr =
                        inputFields.map { it.text }.map { if (it.trim() == "") return@map 0; return@map it.toInt() }
                            .toTypedArray()

                    if (!SudokuSolver.canBePlacedAt(tempArr, i, num)) {
                        e.consume()
                    }
                }
            })
        }
        return inputFields
    }

    fun generateInputPanel(values: Array<Int> = Array(81) { 0 }): InputPanel {
        val fields = createInputFields(values)
        return InputPanel(fields)
    }

    class InputPanel(val inputs: Array<JTextField>) : JPanel()  {

        val sudoku: Sudoku
            get() {
                val values = Utils.mapTextFieldsToInt(inputs)
                return Sudoku(values)
            }

        init {
            layout = GridLayout(9, 9)

            inputs.forEach(::add)
        }
    }
}