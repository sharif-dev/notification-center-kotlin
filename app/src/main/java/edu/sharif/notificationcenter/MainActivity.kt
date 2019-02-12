package edu.sharif.notificationcenter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NotificationCenter.NotificationCenterDelegate {
    private val buttons = mutableListOf<Button>()
    private val textAreas = mutableListOf<EditText>()
    private val textViews = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons.addAll(listOf(button1, button2, button3))
        textAreas.addAll(listOf(textArea1, textArea2, textArea3))
        textViews.addAll(listOf(textView1, textView2, textView3))

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                val targetTextArea = textAreas[index]
                val content = targetTextArea.text.toString()
                targetTextArea.text.clear()
                targetTextArea.clearFocus()
                val msg = Message(index, content) {
                    Log.i(TAG, "Message $index Delivered!")
                }
                NotificationCenter.notifySubscribers(msg)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        NotificationCenter.subscribe(this, 0)
        NotificationCenter.subscribe(this, 1)
        NotificationCenter.subscribe(this, 2)
    }

    override fun onPause() {
        super.onPause()
        NotificationCenter.unSubscribe(this, 0)
        NotificationCenter.unSubscribe(this, 1)
        NotificationCenter.unSubscribe(this, 2)
    }

    override fun receiveData(msg: Message) = runOnUiThread {
        msg.callback()
        textViews[msg.id].text = msg.content
    }

    companion object {
        const val TAG = "MAIN_ACTIVITY"
    }
}
