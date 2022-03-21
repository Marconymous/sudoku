package sudoku.player

import sudoku.model.Sudoku
import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextField

class CustomSudokuPlayer : JPanel() {
    init {
        layout = BorderLayout()

        val spinnerPanel = InputPanel()

        val validateButton = JButton("Validate")
        validateButton.addActionListener {
            val sudoku = spinnerPanel.sudoku
            if (sudoku.isValid()) {
                println("Sudoku is valid")
            } else {
                println("Sudoku is invalid")
            }
        }

        val buttonPanel = JPanel()
        buttonPanel.layout = GridLayout(1, 2)
        buttonPanel.add(validateButton)

        add(spinnerPanel, BorderLayout.CENTER)
        add(buttonPanel, BorderLayout.SOUTH)
    }
}

class SudokuInput : JTextField() {
    init {
        columns = 1

        horizontalAlignment = CENTER

        addKeyListener(object : KeyAdapter() {
            override fun keyTyped(e: KeyEvent) {
                val c = e.keyChar
                if (!Character.isDigit(c)) {
                    e.consume()
                }
            }
        })
    }

    /**
     * @return the value of the input field as an integer
     */
    fun value() = text.let { if (it.isEmpty()) 0 else it.toInt() }
}

class InputPanel : JPanel()  {
    private val inputs: Array<SudokuInput>
    val sudoku: Sudoku
        get() {
            val values = inputs.map { it.value() }.toTypedArray()
            return Sudoku(values)
        }

    init {
        layout = GridLayout(9, 9)
        inputs = Array(81) { SudokuInput() }

        for (i in 0..80) {
            val input = SudokuInput()
            inputs[i] = input
            add(input)
        }
    }
}