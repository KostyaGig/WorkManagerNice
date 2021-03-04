package ru.kostya.workmanagerandmore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .build()

        val request = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build();

        val request2 = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build();

        val request3 = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build();

        WorkManager.getInstance()
            .beginWith(request,request2)
            .then(request2)
            .then(request3)
            .enqueue()

        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id).observe(this, Observer {
            status ->
            Log.d("MyWorker","Onchanged ${status.state}")
        })

        WorkManager.getInstance().getWorkInfoByIdLiveData(request2.id).observe(this, Observer {
                status ->
            Log.d("MyWorker","Onchanged ${status.state}")
        })

        WorkManager.getInstance().getWorkInfoByIdLiveData(request3.id).observe(this, Observer {
                status ->
            Log.d("MyWorker","Onchanged ${status.state}")
        })


        main_tv.setOnClickListener {
            WorkManager.getInstance().cancelWorkById(request.id)
        }

    }
}