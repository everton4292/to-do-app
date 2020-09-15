package com.resende.todolist.presentation.greetings


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.resende.todolist.BuildConfig
import com.resende.todolist.R
import com.resende.todolist.presentation.todo.TodoActivity
import kotlinx.android.synthetic.main.activity_greetings.*
import org.koin.android.viewmodel.ext.android.viewModel


class GreetingsActivity : AppCompatActivity() {

    private val viewModel: GreetingsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greetings)

        val githubLogin = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/login/oauth/authorize?client_id=509686cc79c90a744dea&scope=repo%20gist")
        )
        greetings_button.setOnClickListener { startActivity(githubLogin) }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.tokenAccess.observe(this, Observer { accessToken ->
            if (accessToken != null) {
                successToken(accessToken.accessToken)
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
        val clientId = "{insira seu clientId}"
        val clientSecret = "{insira seu clientSecret}"

        if (uri != null) {
            val code = uri.getQueryParameter("code")
            viewModel.getAccessToken(code, clientId, clientSecret)
        }
    }
}
