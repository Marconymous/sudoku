package sudoku.solver

import sudoku.model.Solution
import sudoku.model.SolutionStep
import sudoku.model.Sudoku
import java.util.*
import javax.swing.JButton

object SudokuSolver {
    fun solve(sudoku: Sudoku): Solution {
        val board = sudoku.board.copyOf()
        println("Board: ${board.contentToString()}")
        val solutionSteps = Stack<SolutionStep>()

        val stopWatch = StopWatch(true)
        val solved = solve(board, solutionSteps, 0)
        val time = stopWatch.elapsed()

        val stats = SolverStatistics(time, solutionSteps.size)

        println("SudokuSize: ${sudoku.board.size}")

        println("Steps: $solutionSteps")

        return Solution(
            sudoku = Sudoku(board),
            solutionSteps = solutionSteps,
            possible = solved,
            stats = stats
        )
    }

    private fun solve(board: Array<Int>, steps: Stack<SolutionStep>, index: Int): Boolean {
        if (index == 81) {
            return true
        }
        if (board[index] != 0) {
            return solve(board, steps, index + 1)
        }
        for (i in 1..9) {
            if (canBePlacedAt(board, index, i)) {
                board[index] = i
                steps.push(SolutionStep(index, i))

                if (solve(board,steps, index + 1)) {
                    return true
                }
            }
        }

        board[index] = 0
        steps.push(SolutionStep(index, 0))
        return false
    }


    fun canBePlacedAt(arr: Array<Int>, index: Int, num: Int): Boolean {
        return !isInRow(arr, index, num) && !isInCol(arr, index, num) && !isInBox(arr, index, num)
    }

    private fun isInRow(arr: Array<Int>, index: Int, num: Int): Boolean {
        val row = index / 9
        for (i in 0..8) {
            if (arr[row * 9 + i] == num) {
                return true
            }
        }
        return false
    }

    private fun isInCol(arr: Array<Int>, index: Int, num: Int): Boolean {
        val col = index % 9
        for (i in 0..8) {
            if (arr[i * 9 + col] == num) {
                return true
            }
        }
        return false
    }

    private fun isInBox(arr: Array<Int>, index: Int, num: Int): Boolean {
        val box = getBox(index)
        for (i in 0..8) {
            if (arr[box / 3 * 27 + box % 3 * 3 + i / 3 * 9 + i % 3] == num) {
                return true
            }
        }
        return false
    }

    private fun getBox(index: Int): Int {
        val row = index / 9
        val col = index % 9
        return row / 3 * 3 + col / 3
    }


}