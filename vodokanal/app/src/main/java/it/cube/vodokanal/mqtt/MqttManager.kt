package it.cube.vodokanal.mqtt

/**
 * Класс для взаимодействия с Mqtt протоколом:
 * А именно: Удобная подписка и отправка,
 * Хранение истории полученных сообщений
 * Ну и можно добавить отправку сообщений
 */
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MqttManager(
    context: Context,
    brokerUrl: String,
    clientId: String,
    ) {

    private var mqttClient: MqttClient? = null
    private val _messageFlow = MutableLiveData<Pair<String, String>>()
    val messageFlow: LiveData<Pair<String, String>> get() = _messageFlow

    init {
        try {
            val persistence = MemoryPersistence()
            mqttClient = MqttClient(brokerUrl, clientId, persistence)
            mqttClient?.setCallback(object : MqttCallbackExtended {
                override fun connectComplete(reconnect: Boolean, serverURI: String) {
                    Log.d("MQTT", "Connection complete. Reconnect: $reconnect")
                    // Подписки можно добавить здесь, чтобы они восстанавливались при переподключении
                }

                override fun connectionLost(cause: Throwable?) {
                    Log.d("MQTT", "Connection lost: ${cause?.message}")
                }

                override fun messageArrived(topic: String, message: MqttMessage) {
                    val payload = String(message.payload)
                    Log.d("MQTT", "Message arrived: $topic - $payload")
                    _messageFlow.postValue(Pair(topic, payload)) // Отправка данных в LiveData
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {
                    // Не используется для подписки
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun connect(options: MqttConnectOptions = MqttConnectOptions()) {
        try {
            options.isCleanSession = true
            mqttClient?.connect(options)
            Log.d("MQTT", "Connected to broker")
        } catch (e: MqttException) {
            Log.e("MQTT", "Failed to connect: ${e.message}")
            e.printStackTrace()
        }
    }
    fun disconnect() {
        try {
            mqttClient?.disconnect()
            Log.d("MQTT", "Disconnected from broker")
        } catch (e: MqttException) {
            Log.e("MQTT", "Failed to disconnect: ${e.message}")
        }
    }

    fun subscribe(topic: String, qos: Int = 0) {
        try {
            mqttClient?.subscribe(topic, qos)
            Log.d("MQTT", "Subscribed to topic: $topic")
        } catch (e: MqttException) {
            Log.e("MQTT", "Failed to subscribe: ${e.message}")
        }
    }

    fun unsubscribe(topic: String){
        try {
            mqttClient?.unsubscribe(topic)
            Log.d("MQTT", "Subscribed to topic: $topic")
        } catch (e: MqttException) {
            Log.e("MQTT", "Failed to subscribe: ${e.message}")
        }
    }
}