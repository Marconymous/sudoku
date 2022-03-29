package sudoku.lang

import sudoku.utils.Configuration

enum class UIText(private val english: String, private val german: String) {
    WINDOW_TITLE("Sudoku Solver","Sudoku Löser"),
    SOLVER_TAB_NAME("Solver", "Löser"),
    SOLVER_SOLVE_BUTTON_NAME("Solve", "Lösen"),
    CUSTOM_TAB_NAME("Custom", "Eigenes"),
    CUSTOM_USE_BUTTON_NAME("Use", "Verwenden"),
    CUSTOM_RESET_BUTTON_NAME("Reset", "Zurücksetzen"),
    PLAY_TAB_NAME("Play", "Spielen"),
    PLAY_VALIDATE_BUTTON_NAME("Validate", "Validieren"),
    SETTINGS_TAB_NAME("Settings", "Einstellungen"),
    SETTINGS_RANDOM_BUTTON_NAME("Random", "Zufällig"),
    SETTINGS_SELECT_THEME_BUTTON_NAME("Select Theme", "Farbschema auswählen"),
    SETTINGS_OPEN_CONFIG_BUTTON_NAME("Open Config", "Konfiguration öffnen");

    fun get(): String {
        return when (Configuration.language) {
            Lang.ENGLISH -> english
            Lang.GERMAN -> german
        }
    }

    companion object {
        operator fun get(id: String): String {
            return values().first { it.name == id }.get()
        }
    }
}

enum class Lang {
    ENGLISH,
    GERMAN
}