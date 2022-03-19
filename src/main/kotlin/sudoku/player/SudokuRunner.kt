package sudoku.player

import sudoku.model.Sudoku
import sudoku.utils.Theme
import sudoku.utils.argparser.ArgParser
import java.awt.Dimension
import javax.swing.*

/**
 * The main class of the Sudoku player.
 * @author Marc Andri Fuchs (Marconymous)
 * @version 1.0.0
 * @since 1.0.0
 */
class SudokuRunner(private var sudoku: Sudoku) : JFrame() {
    private val tabPane = JTabbedPane()

    init {
        title = "Sudoku Solver"
        defaultCloseOperation = EXIT_ON_CLOSE

        val sudokuView = SudokuView(sudoku)
        val settingsView = SettingsView(sudokuView)
        val customInput = CustomInputView(sudokuView)

        tabPane.addTab("Solver", sudokuView)
        tabPane.addTab("Settings", settingsView)
        tabPane.addTab("Custom", customInput)

        add(tabPane)

        size = Dimension(450, 500)
        isVisible = true
    }
}

fun main(args: Array<String>) {
    val theme = ArgParser.parseTheme(args)
    val appliedTheme = Theme.applyTheme(theme)

    println("Applied theme: $appliedTheme")

    SudokuRunner(getDefaultSudoku())

}

fun getDefaultSudoku(): Sudoku {
    return Sudoku(
        Array(81) { 0 }
    )
}