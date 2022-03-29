package sudoku.player

import sudoku.lang.UIText
import sudoku.utils.ComponentFactory
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextField

class CustomInputView(root: SudokuView, player: CustomSudokuPlayer) : JPanel() {
    private val inputFields: Array<JTextField>

    init {
        layout = BorderLayout()

        val inputPanel = JPanel(BorderLayout())

        val inputFieldPanel = ComponentFactory.generateInputPanel()
        inputFields = inputFieldPanel.inputs

        inputPanel.add(inputFieldPanel, BorderLayout.CENTER)

        val buttonPanel = JPanel(GridLayout(1, 2))
        val sendToSolverButton = JButton(UIText["CUSTOM_USE_BUTTON_NAME"])
        sendToSolverButton.addActionListener {
            val s = inputFieldPanel.sudoku

            root.setSudoku(s)
            player.setSudoku(s)
        }

        val reset = JButton(UIText["CUSTOM_RESET_BUTTON_NAME"])
        reset.addActionListener {
            inputFields
        }

        buttonPanel.add(sendToSolverButton)
        buttonPanel.add(reset)

        inputPanel.add(buttonPanel, BorderLayout.SOUTH)

        add(inputPanel, BorderLayout.CENTER)

        size = Dimension(450, 470)
        isVisible = true
    }
}