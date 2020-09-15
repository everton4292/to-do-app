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

        if (!isTaskRoot && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
            && intent.action != null
            && intent.action.equals(Intent.ACTION_MAIN)
        ) {
            finish()
            return
        }

        val githubLogin = Intent(
            Intent.ACTION_VIEW, //insira abaixo o "client_id=" e o "scope=" após o "authorize?"
            Uri.parse("https://github.com/login/oauth/authorize?{seu client_id} e o {scope} aqui")
        )
        greetings_button.setOnClickListener { startActivity(githubLogin) }

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
        //clientId e clientSecret recuperados do Oauth App do Github para realização do processo
        //insira o seu aqui
        val clientId = "{seu clientId}"
        val clientSecret = "{seu clientSecret}"

        if (uri != null) {
            val code = uri.getQueryParameter("code")
            viewModel.getAccessToken(code, clientId, clientSecret)
        }
    }
}
