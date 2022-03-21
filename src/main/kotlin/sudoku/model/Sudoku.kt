package sudoku.model

import sudoku.solver.SudokuSolver

class Sudoku(val board: Array<Int>) {
    fun print() {
        for (i in 0..8) {
            if (i % 3 == 0 && i != 0) {
                println("---------------------")
            }
            for (j in 0..8) {
                if (j == 3 || j == 6) {
                    print("| ")
                }
                print("${board[i * 9 + j]} ")
            }
            println()
        }
    }

    operator fun get(it: Int): Int {
        return board[it]
    }

    fun isValid(): Boolean {
        TODO("isValid not implemented")
    }

    init {
        require(board.size == 81) { "Board must be 9x9" }
    }
}
