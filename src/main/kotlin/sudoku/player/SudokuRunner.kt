package sudoku.player

import sudoku.lang.UIText
import sudoku.lang.UIText.*
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
        title = WINDOW_TITLE.get()
        defaultCloseOperation = EXIT_ON_CLOSE

        val sudokuView = SudokuView(sudoku)
        val settingsView = SettingsView(sudokuView, this)
        val customPlayer = CustomSudokuPlayer()
        val customInput = CustomInputView(sudokuView, customPlayer)

        tabPane.addTab(UIText["SOLVER_TAB_NAME"], sudokuView)
        tabPane.addTab(UIText["CUSTOM_TAB_NAME"], customInput)
        tabPane.addTab(UIText["PLAY_TAB_NAME"], customPlayer)
        tabPane.addTab(UIText["SETTINGS_TAB_NAME"], settingsView)

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