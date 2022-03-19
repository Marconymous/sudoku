package sudoku.utils

import java.awt.Frame
import java.awt.Window
import javax.swing.LookAndFeel
import javax.swing.SwingUtilities
import javax.swing.UIManager


object ThemeUpdater {
    fun updateTheme(laf: LookAndFeel) {
        if (UIManager.getLookAndFeel() == laf) {
            return
        }

        UIManager.setLookAndFeel(laf)
        for (frame in Frame.getFrames()) {
            updateLAFRecursively(frame)
        }
    }

    private fun updateLAFRecursively(window: Window) {
        for (childWindow in window.ownedWindows) {
            updateLAFRecursively(childWindow)
        }
        SwingUtilities.updateComponentTreeUI(window)
    }
}