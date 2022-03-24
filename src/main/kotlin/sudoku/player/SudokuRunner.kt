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
class SudokuRunner(sudoku: Sudoku) : JFrame() {
    private val tabPane = JTabbedPane()

    init {
        title = "Sudoku Solver"
        defaultCloseOperation = EXIT_ON_CLOSE

        val sudokuView = SudokuView(sudoku)
        val settingsView = SettingsView(sudokuView, this)
        val customPlayer = CustomSudokuPlayer()
        val customInput = CustomInputView(sudokuView, customPlayer)

        tabPane.addTab("Solver", sudokuView)
        tabPane.addTab("Custom", customInput)
        tabPane.addTab("Play", customPlayer)
        tabPane.addTab("Settings", settingsView)

        add(tabPane)

        size = Dimension(450, 510)
        isResizable = false
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