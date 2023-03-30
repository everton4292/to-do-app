package com.resende.todolist.presentation.greetings


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.resende.todolist.R
import com.resende.todolist.databinding.ActivityGreetingsBinding
import com.resende.todolist.presentation.todo.TodoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class GreetingsActivity : AppCompatActivity() {

    private val viewModel: GreetingsViewModel by viewModel()
    private lateinit var binding: ActivityGreetingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreetingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val githubLogin = Intent(
            Intent.ACTION_VIEW, //insira abaixo o "client_id=" e o "scope=" após o "authorize?"
            Uri.parse("https://github.com/login/oauth/authorize?client_id=509686cc79c90a744dea&scope=repo%20gist")
        )
        binding.greetingsButton.setOnClickListener { startActivity(githubLogin) }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.tokenAccess.observe(this, Observer { accessToken ->
            if (accessToken != null) {
                successToken(accessToken.accessToken)
                println(accessToken)
            } else {
                errorToken()
            }
        })
    }

    private fun successToken(token: String) {
        Toast.makeText(this, "Conexão bem sucedida", Toast.LENGTH_LONG)
            .show()

        val todoActivityIntent = Intent(this, TodoActivity::class.java)

        val alertDialogToken = AlertDialog.Builder(this)
            .setTitle("Seu Token")
            .setMessage(token)
            .setPositiveButton(
                getString(R.string.ok_alert_string)
            ) { _, _ -> startActivity(todoActivityIntent) }.show()


        alertDialogToken.setOnCancelListener {
            startActivity(todoActivityIntent)
        }
    }

    private fun errorToken() {
        Toast.makeText(
            this,
            "Erro de conexão, contate o administrador",
            Toast.LENGTH_LONG
        ).show()
    }


    override fun onResume() {
        super.onResume()

        val uri: Uri? = intent.data
        val clientId = "509686cc79c90a744dea"
        val clientSecret = "1f5add21a014914ea47ee3b44c41323f26d4ee0e"

        if (uri != null) {
            val code = uri.getQueryParameter("code")
            viewModel.getAccessToken(code, clientId, clientSecret)
        }
    }
}
