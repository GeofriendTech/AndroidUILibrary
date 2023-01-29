package `in`.geofriend.androiduilibrary.table

import `in`.geofriend.androiduilibrary.R
import `in`.geofriend.tableuibuilder.TableUIBuilder
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

class TableUIBuilderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_uibuilder)
        example1()
    }

    private fun example1() {
        (findViewById<View>(R.id.tableContainer) as ViewGroup).addView(
            TableUIBuilder(this, "students_records.csv")
                .setDefaultCellBackgroundColor(Color.WHITE)
                .setRowBackgroundColor(Color.LTGRAY, 0) // setting background color of 0th row
                .setRowTextStyle(Typeface.BOLD, 0) // setting text style of 0th row
                .setRowTextColor(Color.DKGRAY, 0) // setting text color of 0th Row
                .build()
        )
    }

    private fun example2() {
        (findViewById<View>(R.id.tableContainer) as ViewGroup).addView(
            TableUIBuilder(this, "students_records_dynamic.csv")
                .setDefaultCellBackgroundColor(Color.WHITE)
                .setRowBackgroundColor(Color.GRAY, 0)
                .setRowTextStyle(Typeface.BOLD, 0)
                .setRowTextColor(Color.DKGRAY, 0)
                .bind(getStudentsDataAdapter(getStudentData())) // data binding
                .build()
        )
    }

    private fun example3() {
        (findViewById<View>(R.id.tableContainer) as ViewGroup).addView(
            TableUIBuilder(this, "students_records_dynamic1.csv")
                .bind(getStudentsDataAdapter1(getStudentData()))
                .setDefaultCellBackgroundColor(Color.WHITE)
                .setRowBackgroundColor(Color.GRAY, 0)
                .setRowTextStyle(0, Typeface.BOLD)
                .setRowTextColor(Color.DKGRAY, 0)
                .setRowBackgroundColor(Color.LTGRAY, 1)
                .build()
        )
    }

    private fun getStudentsDataAdapter(students: List<Student>): TableUIBuilder.TableDataAdapter? {
        return object : TableUIBuilder.TableDataAdapter() {
            override fun getRowCount(templateRowIndex: Int): Int {
                return if (templateRowIndex == 1) {
                    students.size
                } else super.getRowCount(templateRowIndex)
            }

            override fun getValue(columnName: String, templateRowIndex: Int, index: Int): String {
                if (templateRowIndex == 1) {
                    when (columnName) {
                        "student_name" -> return students[index].name
                        "course" -> return students[index].course
                        "score" -> return students[index].score
                    }
                }
                return super.getValue(columnName, templateRowIndex, index)
            }
        }
    }

    private fun getStudentsDataAdapter1(students: List<Student>): TableUIBuilder.TableDataAdapter? {
        return object : TableUIBuilder.TableDataAdapter() {
            override fun getRowCount(templateRowIndex: Int): Int {
                return if (templateRowIndex == 2) {
                    students.size
                } else super.getRowCount(templateRowIndex)
            }

            override fun getValue(columnName: String, templateRowIndex: Int, index: Int): String {
                if (templateRowIndex == 0) {
                    when (columnName) {
                        "school_name" -> return "Stanford University"
                        "city" -> return "Stanford"
                        "school_rank" -> return "1"
                    }
                } else if (templateRowIndex == 2) {
                    when (columnName) {
                        "student_name" -> return students[index].name
                        "course" -> return students[index].course
                        "score" -> return students[index].score
                    }
                }
                return super.getValue(columnName, templateRowIndex, index)
            }
        }
    }

    private fun getAdapter(): TableUIBuilder.TableDataAdapter? {
        return object : TableUIBuilder.TableDataAdapter() {
            override fun getValue(columnName: String, templateRowIndex: Int, index: Int): String {
                if (templateRowIndex == 0) {
                    return "$columnName: $index"
                } else if (templateRowIndex == 2) {
                    return "$columnName: $index"
                }
                return super.getValue(columnName, templateRowIndex, index)
            }

            override fun getRowCount(templateRowIndex: Int): Int {
                return if (templateRowIndex == 2) {
                    5
                } else super.getRowCount(templateRowIndex)
            }
        }
    }

    private fun getStudentData(): List<Student> {
        val students = ArrayList<Student>()
        students.add(Student("Raghvan", "Ancient Mythology", "100"))
        students.add(Student("Krishna", "Politics", "98"))
        students.add(Student("Chanakya", "Economics", "98"))
        return students
    }

    internal class Student(var name: String, var course: String, var score: String)
}