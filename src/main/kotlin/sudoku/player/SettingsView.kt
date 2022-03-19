package sudoku.player

import sudoku.solver.SudokuGenerator
import sudoku.utils.Theme
import sudoku.utils.ThemeUpdater
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JOptionPane
import javax.swing.JPanel

class SettingsView(private val sudokuView: SudokuView) : JPanel() {

    // The Button to generate a random Sudoku
    private val randomButton = JButton("Random")

    private val themeSelector = JButton("Select Theme")

    init {
        layout = GridLayout(3, 0)

        randomButton.addActionListener {
            sudokuView.setSudoku(SudokuGenerator.generate())
        }
        add(randomButton)

        themeSelector.addActionListener {
            val options = Theme.values().map { it.themeName }.filter { it != "Default" }.toTypedArray()

            val new = JOptionPane.showInputDialog(
                null, "Select a theme", "Theme", JOptionPane.QUESTION_MESSAGE, null, options, options[0]
            ).toString()

            val newTheme = Theme.getThemeByName(new)

            newTheme.theme?.let { it1 -> ThemeUpdater.updateTheme(it1) }
        }
        add(themeSelector)
    }
}