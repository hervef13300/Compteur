package fr.provencecloud.compteur

import android.content.Context
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var count: Int = 0

    private lateinit var digitsText: TextView
    private lateinit var btnPlus: MaterialButton
    private lateinit var btnMinus: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById<MaterialToolbar>(R.id.toolbar))

        digitsText = findViewById(R.id.digitsText)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)

        count = savedInstanceState?.getInt(STATE_COUNT)
            ?: prefs().getInt(PREF_COUNT, 0)

        btnPlus.setOnClickListener {
            if (count < MAX_COUNT) {
                count++
                save()
                updateDisplay()
                it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            }
        }

        btnMinus.setOnClickListener {
            if (count > MIN_COUNT) {
                count--
                save()
                updateDisplay()
                it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            }
        }

        updateDisplay()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_COUNT, count)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_reset -> { confirmReset(); true }
        R.id.action_about -> { showAbout(); true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun confirmReset() {
        AlertDialog.Builder(this)
            .setTitle(R.string.reset_title)
            .setMessage(R.string.reset_message)
            .setPositiveButton(R.string.reset_confirm) { _, _ ->
                count = 0
                save()
                updateDisplay()
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun showAbout() {
        AlertDialog.Builder(this)
            .setTitle(R.string.about_title)
            .setMessage(R.string.about_message)
            .setPositiveButton(R.string.ok, null)
            .show()
    }

    private fun updateDisplay() {
        digitsText.text = "%04d".format(count)
    }

    private fun prefs() = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private fun save() {
        prefs().edit().putInt(PREF_COUNT, count).apply()
    }

    companion object {
        private const val PREFS_NAME = "compteur_prefs"
        private const val PREF_COUNT = "count"
        private const val STATE_COUNT = "state_count"
        private const val MIN_COUNT = 0
        private const val MAX_COUNT = 9999
    }
}
