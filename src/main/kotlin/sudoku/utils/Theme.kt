package sudoku.utils

import com.formdev.flatlaf.FlatDarculaLaf
import com.formdev.flatlaf.FlatIntelliJLaf
import com.formdev.flatlaf.FlatLaf
import com.formdev.flatlaf.intellijthemes.*
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme
import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*
import javax.swing.UIManager
import kotlin.system.exitProcess

enum class Theme(val themeName: String, val theme: FlatLaf?, val programArgName: String) {
    DEFAULT("Default", null, "default"),
    DARCULA("Darcula", FlatDarculaLaf(), "darcula"),
    LIGHT("Light", FlatIntelliJLaf(), "light"),
    PURPLE("Purple", FlatDarkPurpleIJTheme(), "purple"),
    HIBERBEE("Hiberbee", FlatHiberbeeDarkIJTheme(), "hiberbee"),
    ARC_DARK("Arc Dark", FlatArcDarkIJTheme(), "arc-dark"),
    ARC("Arc", FlatArcIJTheme(), "arc"),
    CYAN_LIGHT("Cyan Light", FlatCyanLightIJTheme(), "cyan-light"),
    ARC_ORANGE("Arc - Orange", FlatArcOrangeIJTheme(), "arc-orange"),
    ARC_DARK_ORANGE("Arc Dark - Orange", FlatArcDarkOrangeIJTheme(), "arc-dark-orange"),
    CARBON("Carbon", FlatCarbonIJTheme(), "carbon"),
    COBALT_2("Cobalt 2", FlatCobalt2IJTheme(), "cobalt-2"),
    DARK_FLAT("Dark Flat", FlatDarkFlatIJTheme(), "dark-flat"),
    DRACULA("Dracula", FlatDraculaIJTheme(), "dracula"),
    GRADIANTO_DARK_FUCHSIA("Gradianto Dark Fuchsia", FlatGradiantoDarkFuchsiaIJTheme(), "gradianto-dark-fuchsia"),
    GRADIANTO_DEEP_OCEAN("Gradianto Deep Ocean", FlatGradiantoDeepOceanIJTheme(), "gradianto-deep-ocean"),
    GRADIANTO_MIDNIGHT_BLUE("Gradianto Midnight Blue", FlatGradiantoMidnightBlueIJTheme(), "gradianto-midnight-blue"),
    GRADIANTO_NATURE_GREEN("Gradianto Nature Green", FlatGradiantoNatureGreenIJTheme(), "gradianto-nature-green"),
    GRAY("Gray", FlatGrayIJTheme(), "gray"),
    GRUVBOX_DARK_HARD("Gruvbox Dark Hard", FlatGruvboxDarkHardIJTheme(), "gruvbox-dark-hard"),
    GRUVBOX_DARK_MEDIUM("Gruvbox Dark Medium", FlatGruvboxDarkMediumIJTheme(), "gruvbox-dark-medium"),
    GRUVBOX_DARK_SOFT("Gruvbox Dark Soft", FlatGruvboxDarkSoftIJTheme(), "gruvbox-dark-soft"),
    HIGH_CONTRAST("High Contrast", FlatHighContrastIJTheme(), "high-contrast"),
    LIGHT_FLAT("Light Flat", FlatLightFlatIJTheme(), "light-flat"),
    MATERIAL_DESIGN_DARK("Material Design Dark", FlatMaterialDesignDarkIJTheme(), "material-design-dark"),
    MONOCAI("Monocai", FlatMonocaiIJTheme(), "monocai"),
    NORD("Nord", FlatNordIJTheme(), "nord"),
    ONE_DARK("One Dark", FlatOneDarkIJTheme(), "one-dark"),
    SOLARIZED_DARK("Solarized Dark", FlatSolarizedDarkIJTheme(), "solarized-dark"),
    SOLARIZED_LIGHT("Solarized Light", FlatSolarizedLightIJTheme(), "solarized-light"),
    SPACEGRAY("SpaceGray", FlatSpacegrayIJTheme(), "spacegray"),
    VUESION("Vuesion", FlatVuesionIJTheme(), "vuesion"),
    GITHUB("Github", FlatGitHubIJTheme(), "github"),
    GITHUB_CONTRAST("Github Contrast", FlatGitHubContrastIJTheme(), "github-contrast"),
    GITHUB_DARK("Github Dark", FlatGitHubDarkIJTheme(), "github-dark"),
    GITHUB_DARK_CONTRAST("Github Dark Contrast", FlatGitHubDarkContrastIJTheme(), "github-dark-contrast"),
    NIGHT_OWL("Night Owl", FlatNightOwlIJTheme(), "night-owl"),
    NIGHT_OWL_CONTRAST("Night Owl Contrast", FlatNightOwlContrastIJTheme(), "night-owl-contrast");

    companion object {
        fun getThemeByArgName(themeName: String): Theme {
            return try {
                values().first { it.programArgName == themeName }
            } catch (e: Exception) {
                System.err.println("Theme $themeName not found")
                println("Available themes:")
                values().forEach { println("\t-${it.programArgName}") }

                exitProcess(1)
            }
        }

        fun getThemeByName(themeName: String): Theme {
            return try {
                values().first { it.themeName == themeName }
            } catch (e: Exception) {
                DEFAULT
            }
        }


        fun applyTheme(theme: Theme): Boolean {
            theme.theme?.let {
                ThemeUpdater.updateTheme(it)
                return true
            }

            return false
        }
    }
}