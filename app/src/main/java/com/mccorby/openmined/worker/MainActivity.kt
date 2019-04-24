package com.mccorby.openmined.worker

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mccorby.openmined.worker.datasource.SyftWebSocketDataSource
import com.mccorby.openmined.worker.domain.SyftMessage
import com.mccorby.openmined.worker.domain.SyftOperand
import com.mccorby.openmined.worker.domain.SyftRepository
import com.mccorby.openmined.worker.domain.TensorIdGenerator
import com.mccorby.openmined.worker.framework.DL4JOperations
import com.mccorby.openmined.worker.framework.toINDArray
import com.mccorby.openmined.worker.ui.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_initiate.setOnClickListener { viewModel.initiateCommunication() }

        injectDependencies()
    }

    // TODO Inject using Kodein or another DI framework
    private fun injectDependencies() {
        val clientId = "Android-${System.currentTimeMillis()}"
        val webSocketUrl = "http://10.0.2.2:5000"
        val syftDataSource = SyftWebSocketDataSource(webSocketUrl, clientId)
        val tensorIdGenerator = TensorIdGenerator()
        val syftRepository = SyftRepository(syftDataSource, tensorIdGenerator)
        val mlFramework = DL4JOperations()

        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(syftRepository, mlFramework)
        ).get(MainViewModel::class.java)

        viewModel.syftMessageState.observe(this, Observer<SyftMessage> {
            log_area.append(it.toString() + "\n")

        })
        viewModel.syftTensorState.observe(this, Observer<SyftOperand.SyftTensor> {
            log_area.append(it!!.toINDArray().toString() + "\n")
        })
        viewModel.viewState.observe(this, Observer {
            log_area.append(it + "\n")
        })
    }
}
