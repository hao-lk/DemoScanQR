package vn.alan.demoscanqr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnScan.setOnClickListener {
            val integrator = IntentIntegrator(this).apply {
                setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                setPrompt("Scan")
                setCameraId(0)
                setBeepEnabled(false)
                setBarcodeImageEnabled(false)
            }
            integrator.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (intentResult != null) {
            if (intentResult.contents == null) {
                Log.e("HAO", "Cancel")
            } else {
                Log.e("HAO", "Scan ${intentResult.contents}")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
