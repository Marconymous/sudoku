package sudoku.solver

/**
 * Measures time
 *
 * @author Marc-Andri Fuchs (Marconymous)
 * @version 1.0
 */
class StopWatch(instaStart: Boolean) {
    private var startTime: Long = 0
    private var stopTime: Long = -1

    init {
        if (instaStart) start()
    }

    constructor() : this(false)


    /**
     * Starts the timer
     */
    fun start() {
        startTime = System.currentTimeMillis()
    }

    /**
     * Stops the timer
     */
    fun stop() {
        stopTime = System.currentTimeMillis()
    }

    /**
     * @return if stopwatch is stopped the return value is Start Time minus Stop Time, else it is Current Time - Start Time
     */
    fun elapsed(): Long {
        return if (stopTime >= 0) {
            stopTime - startTime
        } else System.currentTimeMillis() - startTime
    }

    /**
     *
     * @return Time formatted in HH:MM:SS:ms format
     */
    override fun toString(): String {
        val milliseconds = elapsed()
        val seconds = (milliseconds / 1000).toInt() % 60
        val minutes = (milliseconds / (1000 * 60) % 60).toInt()
        val hours = (milliseconds / (1000 * 60 * 60) % 24).toInt()
        val millis = (milliseconds - seconds * 1000 - minutes * 1000 * 60 - hours * 1000 * 60 * 60).toInt()
        return String.format("%dh, %dmin, %ds, %dms", hours, minutes, seconds, millis)
    }
}