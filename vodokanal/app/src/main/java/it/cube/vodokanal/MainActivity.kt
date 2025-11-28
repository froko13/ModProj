package it.cube.vodokanal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.cube.vodokanal.databinding.ActivityMainBinding
import it.cube.vodokanal.ui.theme.VodokanalTheme

class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
//            Обработчики нажатий для кнопок.  (нужно как-то подключиться к топику и обновлять сообщения.)
            BConnectToTopic1.setOnClickListener {  }

            BConnectToTopic2.setOnClickListener {  }
        }
    }
}
