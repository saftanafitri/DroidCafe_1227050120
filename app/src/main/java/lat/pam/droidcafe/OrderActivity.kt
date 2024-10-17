package lat.pam.droidcafe

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {

    private lateinit var spinnerCity: Spinner
    private lateinit var editTextNote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        // Inisialisasi Spinner dan EditText
        spinnerCity = findViewById(R.id.spinnerCity)
        editTextNote = findViewById(R.id.editTextNote)

        // Mengisi data Spinner dengan array dari resources
        ArrayAdapter.createFromResource(
            this,
            R.array.city_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCity.adapter = adapter
        }

        // Atur WindowInsets agar sesuai dengan edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton).isChecked
        when (view.id) {
            R.id.sameday -> if (checked) // Same day service
                displayToast(getString(R.string.same_day_messenger_service))

            R.id.nextday -> if (checked) // Next day delivery
                displayToast(getString(R.string.next_day_ground_delivery))

            R.id.pickup -> if (checked) // Pick up
                displayToast(getString(R.string.pick_up))
        }
    }

    private fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun submitOrder(view: View) {
        // Ambil input dari Spinner dan EditText
        val selectedCity = spinnerCity.selectedItem.toString()
        val note = editTextNote.text.toString()

        // Tampilkan Toast untuk mengonfirmasi pesanan
        val orderSummary = "City: $selectedCity\nNote: $note"
        Toast.makeText(this, orderSummary, Toast.LENGTH_LONG).show()
    }
}
