package ru.kostya.workmanagerandmore

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        for(i in 0..10){
            TimeUnit.MILLISECONDS.sleep(1000);
            Log.d(Companion.TAG, "doWork: $i" )
            if (isStopped){
                return Result.failure()
            }
        }

        Log.d(TAG, "doWork: finished")
        return Result.success()
    }

    companion object {
        private const val TAG = "MyWorker"
    }

    override fun onStopped() {
        super.onStopped()
        Log.d(TAG, "onStopped: ")
    }
}