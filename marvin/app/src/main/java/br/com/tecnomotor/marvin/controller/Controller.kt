package br.com.tecnomotor.marvin.controller

import android.util.Log
import br.com.tecnomotor.commonrail.device.EnumDeviceDefault
import kotlinx.coroutines.*
import java.util.*

open class Controller():Observable(), IController {

    private var tagLog = this::class.java.simpleName

    protected var started: Boolean = false
    protected var paused: Boolean = false
    protected var finished: Boolean = true
    var deviceDefault: EnumDeviceDefault = EnumDeviceDefault.Control

    @Synchronized
    fun isStarted() = started
    @Synchronized
    fun isPaused() = paused
    @Synchronized
    fun isFinished() = finished

    protected lateinit var jobController: Job
    @Synchronized
    protected open fun execJob() = GlobalScope.launch(Dispatchers.IO) {
        while (!finished) {
            if (started) {
                if (!paused) {

                }
            }
        }
    }

    @Synchronized
    override fun start() {
        Log.i(tagLog, "Start")
        if (isFinished())
            finished = false
        paused = false
        started = true
        if ((!this::jobController.isInitialized) || (jobController.isCompleted))
            jobController = execJob()
    }

    @Synchronized
    override fun stop() {
        Log.i(tagLog, "Stop")
        started = false
        paused = false
    }

    @Synchronized
    override fun pause() {
        Log.i(tagLog, "Pause")
        paused = true
    }

    @Synchronized
    override fun finish() {
        this.stop()
        finished = true
        if (this::jobController.isInitialized) {
            while (!jobController.isCompleted) {
                Thread.sleep(1000)
            }
            Log.i(tagLog, "Finished")
        }
    }
}