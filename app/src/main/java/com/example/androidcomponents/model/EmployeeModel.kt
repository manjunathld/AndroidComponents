package com.example.androidcomponents.model

import android.os.Parcel
import android.os.Parcelable

data class EmployeeModel(
    val empID: Int,
    val empName: String?,
    val empSalary: Double
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(empID)
        parcel.writeString(empName)
        parcel.writeDouble(empSalary)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmployeeModel> {
        override fun createFromParcel(parcel: Parcel): EmployeeModel {
            return EmployeeModel(parcel)
        }

        override fun newArray(size: Int): Array<EmployeeModel?> {
            return arrayOfNulls(size)
        }
    }
}