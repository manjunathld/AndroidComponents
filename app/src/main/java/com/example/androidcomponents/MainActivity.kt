package com.example.androidcomponents

import android.app.ActivityManager
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.androidcomponents.activities.EmpDetailsActivity
import com.example.androidcomponents.model.EmployeeModel
import com.example.androidcomponents.receiver.ReceiverPowerConnectivity
import com.example.androidcomponents.services.ForegroundService
import android.content.Intent




class MainActivity() : AppCompatActivity() {

    private lateinit var etEmployeeID: EditText
    private lateinit var etEmployeeName: EditText
    private lateinit var etEmployeeSalary: EditText
    private lateinit var bvSubmit: Button
    private lateinit var bvSendBroadcastMessage: Button
    private var isServiceBound: Boolean = false

    private lateinit var receiverPowerConnectivity: ReceiverPowerConnectivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Executors.newSingleThreadScheduledExecutor().schedule({
        }, 2, TimeUnit.SECONDS)*/

        etEmployeeID = findViewById(R.id.et_employee_id)
        etEmployeeName = findViewById(R.id.et_employee_name)
        etEmployeeSalary = findViewById(R.id.et_employee_salary)
        bvSubmit = findViewById(R.id.bv_submit)
        bvSendBroadcastMessage = findViewById(R.id.bv_send_broadcast_message)

        bvSubmit.setOnClickListener(submitClickListener)
        bvSendBroadcastMessage.setOnClickListener(submitClickListener)

        /*//Starting Background Service
        val backgroundService: Intent = Intent(this@MainActivity, BackgroundService::class.java)
        startService(backgroundService)*/

        /*//Starting Foreground Service
        if (!isForegroundServiceRunning()) {
            val foregroundService: Intent = Intent(this@MainActivity, ForegroundService::class.java)
            startForegroundService(foregroundService)
            Toast.makeText(this, "Foreground Service is stating...", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Service is already started...", Toast.LENGTH_SHORT).show()
        }*/

        /*//Bounded Service
        if (!isServiceBound) {
            val boundedServiceIntent = Intent(this@MainActivity, BoundService::class.java)
            bindService(boundedServiceIntent, serviceCreation, BIND_AUTO_CREATE)
        }*/

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        receiverPowerConnectivity = ReceiverPowerConnectivity()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        registerReceiver(receiverPowerConnectivity, intentFilter)

    }

    override fun onPause() {
        unregisterReceiver(receiverPowerConnectivity)
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        if (isServiceBound) {
            unbindService(serviceCreation)
        }
        super.onDestroy()
    }

    private val submitClickListener = View.OnClickListener { view ->
        var employeeID: Int = 0
        var employeeName: String = "No name"
        var employeeSalary: Double = 0.0
        when (view.id) {
            R.id.bv_submit -> {
                if (etEmployeeID.text.isNotEmpty()) {
                    employeeID = (etEmployeeID.text.toString()).toInt()
                }
                if (etEmployeeName.text.isNotEmpty()) {
                    employeeName = (etEmployeeName.text.toString())
                }
                if (etEmployeeSalary.text.isNotEmpty()) {
                    employeeSalary = (etEmployeeSalary.text.toString()).toDouble()
                }

                /*
                //Primitive type
                val bundle: Bundle = Bundle()
                bundle.putInt("employeeID", employeeID)
                bundle.putString("employeeName", employeeName)
                bundle.putDouble("employeeSalary", employeeSalary)
                intent.putExtras(bundle)*/

                val employeeModel: EmployeeModel = EmployeeModel(employeeID, employeeName, employeeSalary)
                val intent: Intent = Intent(this@MainActivity, EmpDetailsActivity::class.java)
                val bundle: Bundle = Bundle()
                bundle.putParcelable("EMPLOYEESMODEL", employeeModel)
                intent.putExtras(bundle)
                startActivity(intent)

            }
            R.id.bv_send_broadcast_message -> {
                //Broadcasting custom intent message
                val customIntent = Intent()
                customIntent.action = "com.example.androidcomponents"
                sendBroadcast(intent)
            }
            else -> {}
        }
    }

    private fun isForegroundServiceRunning(): Boolean {
        val activityManager: ActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (ForegroundService::class.java.name.equals(service.service.className)) {
                return true
            }
        }

        return false
    }

    private val serviceCreation = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            isServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isServiceBound = false
        }
    }
}