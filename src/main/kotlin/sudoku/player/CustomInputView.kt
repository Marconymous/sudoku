package sudoku.player

import sudoku.model.Sudoku
import sudoku.solver.SudokuSolver
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextField

class CustomInputView(root: SudokuView) : JPanel() {
    init {
        layout = BorderLayout()

        val inputPanel = JPanel(BorderLayout())

        val inputFieldPanel = JPanel(GridLayout(9, 9))
        val inputFields = Array(81) { JTextField() }
        for (i in 0..80) {
            val input = inputFields[i]
            input.addKeyListener(object : KeyAdapter() {
                override fun keyTyped(e: KeyEvent) {
                    val c = e.keyChar

                    if (!Character.isDigit(c)) {
                        e.consume()
                        return
                    }

                    val num = c.toString().let { if (it == "") return@let "0"; return@let it }.toInt()

                    val tempArr = inputFields.map { it.text }.map { if (it.trim() == "") return@map 0; return@map it.toInt() }.toTypedArray()

                    if (!SudokuSolver.canBePlacedAt(tempArr, i, num)) {
                        e.consume()
                    }
                }

                override fun keyReleased(e: KeyEvent) {
                    val text = input.text
                    if (text.length > 1) {
                        input.text = text.substring(text.length - 1)
                    }

                    val num = text.let { if (it == "") return@let 0; return@let it.toInt() }
                    if (num < 1 || num > 9) {
                        input.text = ""
                    }
                }
            })

            inputFields[i].horizontalAlignment = JTextField.CENTER

            inputFieldPanel.add(input)
        }

        inputPanel.add(inputFieldPanel, BorderLayout.CENTER)

        val sendToSolverButton = JButton("Send to Solver")
        sendToSolverButton.addActionListener {
            val tempArr = inputFields.map {
                it.text.let { int ->
                    if (int == "") return@let 0
                    return@let int.toInt()
                }
            }.toTypedArray()

            root.setSudoku(Sudoku(tempArr))
        }

        inputPanel.add(sendToSolverButton, BorderLayout.SOUTH)

        add(inputPanel, BorderLayout.CENTER)

        size = Dimension(450, 470)
        isVisible = true
    }
}