package com.example

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.threadstest.R



class MainActivity : AppCompatActivity() {

    private var buttonStartThread: Button? = null

    private val mainHandler = Handler()

    @Volatile
    private var stopThread = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStartThread = findViewById(R.id.button_start_thread)
    }

    fun startThread(view: View) {
        stopThread = false
        val runnable = ExampleRunnable(10)
        Thread(runnable).start()
        /*
        ExampleThread thread = new ExampleThread(10);
        thread.start();
        */
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                //work
            }
        }).start();
        */
    }

    fun stopThread(view: View) {
        stopThread = true
    }

    internal inner class ExampleThread(var seconds: Int) : Thread() {

        override fun run() {
            for (i in 0 until seconds) {
                Log.d(TAG, "startThread: $i")
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }
    }

    internal inner class ExampleRunnable(var seconds: Int) : Runnable {

        override fun run() {
            for (i in 0 until seconds) {
                if (stopThread)
                    return
                if (i == 5) {
                    /*
                    Handler threadHandler = new Handler(Looper.getMainLooper());
                    threadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("50%");
                        }
                    });
                    */
                    /*
                    buttonStartThread.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("50%");
                        }
                    });
                    */
                    runOnUiThread { buttonStartThread!!.setText("50%") }
                }
                Log.d(TAG, "startThread: $i")
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
