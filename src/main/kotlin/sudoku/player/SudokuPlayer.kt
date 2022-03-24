package sudoku.player

import sudoku.model.Sudoku
import sudoku.utils.ComponentFactory
import java.awt.BorderLayout
import java.awt.Color
import java.awt.GridLayout
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import javax.swing.*
import javax.swing.JOptionPane.showMessageDialog

class CustomSudokuPlayer : JPanel() {
    private var panel: ComponentFactory.InputPanel

    init {
        layout = BorderLayout()

        panel = ComponentFactory.generateInputPanel()
        val inputs = panel.inputs

        val validateButton = JButton("Validate")
        validateButton.addActionListener {
            val sudoku = panel.sudoku
            val isValid = sudoku.isValid()
            if (isValid.first) {
                showMessageDialog(null, "Sudoku is valid!\nGood Job :)", "Sudoku is Valid :)", JOptionPane.INFORMATION_MESSAGE)
            } else {
                val incorrectInput = inputs[isValid.second]
                setErrorOnInput(incorrectInput)


                showMessageDialog(null ,"Sudoku is invalid!\nPosition: ${isValid.second + 1}\nValue: ${sudoku[isValid.second]}\nGood Luck :)", "Sudoku is Invalid :(", JOptionPane.ERROR_MESSAGE)
            }
        }

        val buttonPanel = JPanel()
        buttonPanel.layout = GridLayout(1, 2)
        buttonPanel.add(validateButton)

        add(panel, BorderLayout.CENTER)
        add(buttonPanel, BorderLayout.SOUTH)
    }

    private fun setErrorOnInput(incorrectInput: JTextField) {
        incorrectInput.border = BorderFactory.createLineBorder(Color.RED)
        incorrectInput.addFocusListener(object : FocusAdapter() {
            override fun focusGained(e: FocusEvent?) {
                incorrectInput.border = null
                incorrectInput.removeFocusListener(this)
            }
        })
    }

    fun setSudoku(s: Sudoku) {
        remove(panel)
        panel = ComponentFactory.generateInputPanel(s.board)
        add(panel, BorderLayout.CENTER)
    }
}