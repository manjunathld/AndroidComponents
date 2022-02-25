package com.example.androidcomponents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.androidcomponents.activities.EmpDetailsActivity
import com.example.androidcomponents.model.EmployeeModel
import com.example.androidcomponents.services.BackgroundService
import com.example.androidcomponents.services.ForegroundService

class MainActivity() : AppCompatActivity() {

    private lateinit var etEmployeeID: EditText
    private lateinit var etEmployeeName: EditText
    private lateinit var etEmployeeSalary: EditText
    private lateinit var bvSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Executors.newSingleThreadScheduledExecutor().schedule({
        }, 2, TimeUnit.SECONDS)*/

        etEmployeeID = findViewById(R.id.et_employee_id)
        etEmployeeName = findViewById(R.id.et_employee_name)
        etEmployeeSalary = findViewById(R.id.et_employee_salary)
        bvSubmit = findViewById(R.id.bv_submit)

        bvSubmit.setOnClickListener(submitClickListener)

        //Starting Background Service
        //val backgroundService: Intent = Intent(this@MainActivity, BackgroundService::class.java)
        //startService(backgroundService)

        //Starting Foreground Service
        val foregroundService: Intent = Intent(this@MainActivity, ForegroundService::class.java)
        startForegroundService(foregroundService)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
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
            else -> {}
        }
    }

}