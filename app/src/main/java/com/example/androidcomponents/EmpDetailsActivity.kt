package com.example.androidcomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EmpDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_details)

        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            val employeeID: Int = bundle.getInt("employeeID")
            val employeeName: String? = bundle.getString("employeeName")
            val employeeSalary: Double = bundle.getDouble("employeeSalary")

            findViewById<TextView>(R.id.tv_emp_salary).text = employeeSalary.toString()
            findViewById<TextView>(R.id.tv_emp_id).text = employeeID.toString()
            findViewById<TextView>(R.id.tv_emp_name).text = employeeName

        }

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

}