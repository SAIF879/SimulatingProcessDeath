package com.example.simulatingprocessdeath
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.SavedStateViewModelFactory
import com.example.simulatingprocessdeath.ui.theme.SimulatingProcessDeathTheme

class MainActivity : ComponentActivity() {
    private val TAG = "Lifecycle"
    private lateinit var viewModel: SimulationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() - ${if (savedInstanceState != null) "RESTORING" else "COLD START"}")

        viewModel = ViewModelProvider(
            this,
            SavedStateViewModelFactory(application, this)
        )[SimulationViewModel::class.java]

        Log.d(TAG, "Restored text value: '${viewModel.text.value}'")

        setContent {
            SimulatingProcessDeathTheme {
                val text by viewModel.text.collectAsState()

                EditTextDemo(
                    text = text,
                    onTextChange = viewModel::updateText
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() - ${if (isFinishing) "FINISHING" else "PROCESS DEATH"}")
    }
}

@Composable
fun EditTextDemo(
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        label = { Text("Type something...") }
    )
}
