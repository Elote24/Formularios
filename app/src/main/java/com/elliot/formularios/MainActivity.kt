package com.elliot.formularios

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var checkElectronica : CheckBox
    private lateinit var checkHouse : CheckBox
    private lateinit var checkRock : CheckBox
    private lateinit var textGeneros: TextView
    private lateinit var radio1: RadioButton
    private lateinit var radio2: RadioButton
    private lateinit var radio3: RadioButton
    private lateinit var textRadioActivo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switchHabilitar = findViewById<SwitchMaterial>(R.id.switch_habilitar)
        val textHabilitar = findViewById<TextView>(R.id.text_habilitar)
        switchHabilitar.setOnCheckedChangeListener { button, checked ->
            if (checked)
                textHabilitar.text = "Habilitado"
            else
                textHabilitar.text = "Deshabilitado"
        }

        checkElectronica = findViewById(R.id.checbox_electronica)
        checkHouse = findViewById(R.id.checkbox_house)
        checkRock = findViewById(R.id.checkbox_rock)
        textGeneros = findViewById(R.id.text_generos)

        checkElectronica.setOnCheckedChangeListener(changeChecked)
        checkHouse.setOnCheckedChangeListener(changeChecked)
        checkRock.setOnCheckedChangeListener(changeChecked)

        radio1 = findViewById(R.id.radio1)
        radio2 = findViewById(R.id.radio2)
        radio3 = findViewById(R.id.radio3)
        textRadioActivo = findViewById(R.id.text_radioactivo)

        radio1.setOnCheckedChangeListener(radioChecked)
        radio2.setOnCheckedChangeListener(radioChecked)
        radio3.setOnCheckedChangeListener(radioChecked)

        val buttonToast = findViewById<Button>(R.id.button_toast)
        buttonToast.setOnClickListener(clickToast)

        val fabActionDialog = findViewById<FloatingActionButton>(R.id.fabActionDialog)
        fabActionDialog.setOnClickListener(fabClick)

        val fabCalendarDialog = findViewById<FloatingActionButton>(R.id.fabCalendar)
        fabCalendarDialog.setOnClickListener(fabCalendar)
    }

    private val fabCalendar = View.OnClickListener { fab ->
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(fab.context, { datePicker, y, m, dm ->
            val dateFormat = DateFormat.getDateInstance(DateFormat.FULL)
            val cal = Calendar.getInstance()
            cal.set(y, m, dm)
            val date = cal.time
            val dateString = dateFormat.format(date)

            Toast.makeText(datePicker.context, dateString, Toast.LENGTH_LONG).show()
        } , year, month, dayOfMonth)
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+3)
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    private val fabClick = View.OnClickListener {fab ->
        val alertDialog = AlertDialog.Builder(fab.context)
            .setTitle("Alert Dialog de ejemplo")
            .setMessage("Este es un mensaje de ejemplo del alert dialog")
            .setPositiveButton("Ok", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, p1: Int) {
                    Toast.makeText(fab.context, "Click en ok", Toast.LENGTH_LONG).show()
                }
            })
            .setNegativeButton("No", null)
            .create()

        alertDialog.show()
    }

    private val clickToast = View.OnClickListener { view ->
        Toast.makeText(view.context, "Toast de ejemplo", Toast.LENGTH_LONG).show()
    }

    private val changeChecked = CompoundButton.OnCheckedChangeListener { button, checked ->
        var texto = ""

        if (checkElectronica.isChecked) {
            texto += "Electronica\n"
        }

        if (checkHouse.isChecked) {
            texto += "House\n";
        }

        if (checkRock.isChecked) {
            texto += "Rock\n"
        }

        textGeneros.text = texto
    }

    private val radioChecked = CompoundButton.OnCheckedChangeListener { button, checked ->
        var texto = ""

        if (radio1.isChecked) {
            texto = "El radio 1 está activo"
        } else if (radio2.isChecked) {
            texto = "El radio 2 está activo"
        } else if (radio3.isChecked) {
            texto = "El radio 3 está activo"
        }

        textRadioActivo.text = texto
    }
}