package sudoku.player

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*

class ConfigurationDialog(parent: JFrame, private val sudokuView: SudokuView) : JDialog(parent, "Configuration", true) {
    init {
        size = Dimension(200, 100)
        defaultCloseOperation = DISPOSE_ON_CLOSE

        layout = BorderLayout()
        initComponents()

        isVisible = true
    }

    private fun initComponents() {
        val panel = JPanel()
        panel.layout = GridLayout(2, 1)

        val label  = JLabel("Animation Frame Delay")
        val delay = SpinnerNumberModel(1, 0, 100, 1)
        val spinner = JSpinner(delay)

        panel.add(label)
        panel.add(spinner)

        val save = JButton("Save")
        save.addActionListener {
            sudokuView.animationDelay = delay.value as Int
            dispose()
        }

        val cancel = JButton("Cancel")
        cancel.addActionListener {
            dispose()
        }

        val buttonPanel = JPanel()
        buttonPanel.layout = GridLayout(1, 2)

        buttonPanel.add(save)
        buttonPanel.add(cancel)

        add(panel, BorderLayout.CENTER)
        add(buttonPanel, BorderLayout.SOUTH)
    }
}