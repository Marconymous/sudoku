package sudoku.model

import sudoku.solver.SolverStatistics

class Solution(val sudoku: Sudoku, val possible: Boolean, val solutionSteps: List<SolutionStep>, stats: SolverStatistics)

data class SolutionStep(val index: Int, val value: Int)
