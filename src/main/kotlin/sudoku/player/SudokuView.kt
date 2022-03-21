package sudoku.player

import sudoku.model.Solution
import sudoku.model.Sudoku
import sudoku.solver.SudokuSolver
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JOptionPane
import javax.swing.JPanel

class SudokuView(private var sudoku: Sudoku) : JPanel(){
    var animationDelay: Int = 10

    // The Buttons where the Sudoku Numbers will be written
    private val buttons: Array<JButton>

    // The Button to solve the Sudoku
    private val solveButton = JButton("Solve")

    init {
        setSize(800, 600)
        layout = BorderLayout()

        // Create the buttons
        val grid = JPanel(GridLayout(9, 9))
        buttons = Array(81) {
            val btn = JButton()
            btn.isEnabled = false
            setSingleButton(btn, sudoku[it])
            grid.add(btn)

            btn
        }
        add(grid, BorderLayout.CENTER)


        solveButton.addActionListener {
            solveButton.isEnabled = false

            val solved = SudokuSolver.solve(sudoku)
            if (!solved.possible) {
                JOptionPane.showMessageDialog(null, "No solution found")
                return@addActionListener
            }

            showSolutionDialog(solved)

            solveButton.isEnabled = true
        }

        add(solveButton, BorderLayout.SOUTH)

        size = Dimension(450, 470)
        isVisible = true
    }

    private fun showSolutionDialog(solved: Solution) {
        val showSolution =
            JOptionPane.showConfirmDialog(null, "Show solution?", "Show solution", JOptionPane.YES_NO_OPTION)
        if (showSolution == JOptionPane.YES_OPTION) {
            val animateSolution =
                JOptionPane.showConfirmDialog(null, "Animate solution?", "Animate solution", JOptionPane.YES_NO_OPTION)
            if (animateSolution == JOptionPane.YES_OPTION) {
                val runnable = SudokuStepVisualizer(solved, buttons, solveButton, animationDelay)
                Thread(runnable).start()
                return
            }

            for (i in 0 until 81) {
                setSingleButton(buttons[i], solved.sudoku[i])
            }
        }
    }

    private fun setSingleButton(btn: JButton, num: Int = 0) {
        btn.background = if (num == 0) Color.WHITE else Color.LIGHT_GRAY
        btn.text = if (num == 0) "" else num.toString()
    }

    fun setSudoku(sudoku: Sudoku) {
        this.sudoku = sudoku
        for (i in 0 until 81) {
            setSingleButton(buttons[i], sudoku[i])
        }
    }
}