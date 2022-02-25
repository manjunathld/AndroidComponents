package com.example.androidcomponents.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androidcomponents.R
import com.example.androidcomponents.model.EmployeeModel

class EmpDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_details)

        //val bundle: Bundle? = intent.extras
        /*if (bundle != null) {
            //val employeeID: Int = bundle.getInt("employeeID")
            //val employeeName: String? = bundle.getString("employeeName")
            //val employeeSalary: Double = bundle.getDouble("employeeSalary")

            //findViewById<TextView>(R.id.tv_emp_salary).text = employeeSalary.toString()
            //findViewById<TextView>(R.id.tv_emp_id).text = employeeID.toString()
            //findViewById<TextView>(R.id.tv_emp_name).text = employeeName
        }*/

        val employeeBundle: Bundle? = intent.extras
        val employeeModel: EmployeeModel? = employeeBundle!!.getParcelable<EmployeeModel>("EMPLOYEESMODEL")

        val empID: Int
        val empName: String
        val empSalary: Double
        if (employeeModel != null) {
            empID = employeeModel.empID
            empName = employeeModel.empName.toString()
            empSalary = employeeModel.empSalary
        } else {
            empID = 123
            empName = "No Name"
            empSalary = 0.0
        }

        findViewById<TextView>(R.id.tv_emp_id).text = (empID).toString()
        findViewById<TextView>(R.id.tv_emp_name).text = (empName)
        findViewById<TextView>(R.id.tv_emp_salary).text = (empSalary).toString()

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