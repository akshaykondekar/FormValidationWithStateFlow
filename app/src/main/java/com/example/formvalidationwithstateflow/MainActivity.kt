package com.example.formvalidationwithstateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.formvalidationwithstateflow.validation.ValidationViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ValidationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ValidationViewModel::class.java)
        initListeners()
        initObserver()
    }

    private fun initListeners() {
        editText_name.addTextChangedListener {
            viewModel.setFirstName(it.toString())
        }
        editText_password.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }
        editText_user.addTextChangedListener {
            viewModel.setUserId(it.toString())
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.isSubmitEnabled.collect { value ->
                submit_button.isEnabled = value
            }
        }
    }
}