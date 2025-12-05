package it.cube.vodokanal

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import it.cube.vodokanal.databinding.ActivityMainBinding
import it.cube.vodokanal.mqtt.MqttManager
import org.eclipse.paho.client.mqttv3.MqttConnectOptions

class MainActivity : ComponentActivity() {
    private lateinit var mqttManager : MqttManager
    lateinit var binding: ActivityMainBinding
    lateinit var topic1 : String
    lateinit var topic2 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mqttManager = MqttManager(this,MQTT_BROCKER_URL, MQTT_BROCKER_CLIENT_ID)
        val options = MqttConnectOptions()
        options.userName = CLIENT_NAME
        options.password = CLIENT_PASSWORD

        mqttManager.connect()
        binding.apply {
//            Обработчики нажатий для кнопок.  (нужно как-то подключиться к топику и обновлять сообщения.)
            BConnectToTopic1.setOnClickListener {
                topic1 = Topic1EdT.text.toString()
                mqttManager.subscribe(topic1)
            }

            BConnectToTopic2.setOnClickListener {
                topic2 = Topic1EdT.text.toString()
                mqttManager.subscribe(topic2)
            }
        }



        mqttManager.messageFlow.observe(this, Observer{(topic,message)->
            when(topic){
                topic1 -> {
                    binding.showLastMessageTopic1TV.text = message
                }
                topic2 -> {
                    binding.showLastMessageTopic2TV.text = message
                }
            }


        })
    }
}
