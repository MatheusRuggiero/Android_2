package br.com.tecnomotor.marvin.utils

import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.*

@DelicateCoroutinesApi
abstract class CountUpTimer(
    private val countInterval: Long,
    var millisInFuture: Long = 0
) {
    private var tagLog = "CountUpTimer"
    private var mStarted = false
    private var mPaused = false
    private var mStartFromBreak = false
    private var mListOfBreaks: CountTimePausedList = CountTimePausedList()
    private var mFinished = true
    private var mStartedTime: Long = 0
    private var mValueTime: Long = 0

    private var jobTimerController: Job? = null

    class CountTimePause(private val initialPausedTime: Long = 0) {
        private var timePaused: Long = 0
        fun setTimePaused(value: Long) {
            timePaused = value - initialPausedTime
        }
        fun getTimePaused(): Long {
            return this.timePaused
        }
    }
    class CountTimePausedList(): ArrayList<CountTimePause>() {
        fun getTotalTimePaused():Long {
            var value: Long = 0
            this.forEach {
                value += it.getTimePaused()
            }
            return value
        }
    }

    @Synchronized
    fun start(): CountUpTimer {
        Log.i(tagLog, "Start")
        mStarted = true
        mPaused = false
        mListOfBreaks = CountTimePausedList()
        mFinished = false
        mStartedTime = 0
        mValueTime = 0
        if (millisInFuture < 0) {
            onFinish()
            return this
        }
        if (jobTimerController == null)
            jobTimerController = execJob()
        return this
    }

    @Synchronized
    fun start(millisInFuture: Long): CountUpTimer {
        this.millisInFuture = millisInFuture
        return this.start()
    }

    @Synchronized
    fun stop(): CountUpTimer {
        Log.i(tagLog, "Stop")
        mStarted = false
        mFinished = true
        jobTimerController = null
        return this
    }

    @Synchronized
    fun pause() {
        Log.i(tagLog, "Pause")
        mStartFromBreak = true
        mPaused = true
    }

    @Synchronized
    fun continueCount() {
        Log.i(tagLog, "Continue")
        mPaused = false
    }

    @Synchronized
    fun isStarted():Boolean = (mStarted && !mFinished)
    @Synchronized
    fun isFinished():Boolean = mFinished

    abstract fun onTick(millisFinished: Long)

    abstract fun onFinish()

    @Synchronized
    private fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        mStartedTime = SystemClock.elapsedRealtime()
        while (!mFinished) {
            mValueTime = (SystemClock.elapsedRealtime() - mStartedTime)
            if (mStartFromBreak) {
                mListOfBreaks.add(CountTimePause(mValueTime))
                mStartFromBreak = false
            }
            if (mPaused)
                mListOfBreaks.last().setTimePaused(mValueTime)
            delay(countInterval)
            if ((millisInFuture > 0) && (mValueTime > millisInFuture)) {
                break
            }
//            Log.w(tag, "Time: $mValueTime - Time paused: $mTimePaused")
            onTick(mValueTime - mListOfBreaks.getTotalTimePaused())
        }
        stop()
        onFinish()
    }
}