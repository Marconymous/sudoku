package sudoku.utils.argparser

import sudoku.utils.Theme

object ArgParser {
    fun parseTheme(args: Array<String>): Theme {
        return args.find { it.startsWith("--theme=") }?.
        substringAfter("=")?.
        let { Theme.getThemeByArgName(it) } ?: Theme.DEFAULT
    }
}