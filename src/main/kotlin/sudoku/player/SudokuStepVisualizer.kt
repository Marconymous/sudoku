package sudoku.player

import sudoku.model.Solution
import javax.swing.JButton

class SudokuStepVisualizer(
    private val solved: Solution,
    private val buttons: Array<JButton>,
    private val solveButton: JButton,
    private val animationDelay: Int,
) : Runnable {
    override fun run() {
        val steps = solved.solutionSteps

        for (step in steps) {
            val button = buttons[step.index]
            button.text = if (step.value == 0) "" else step.value.toString()
            Thread.sleep(animationDelay.toLong())
        }

        solveButton.isEnabled = true
    }

}
