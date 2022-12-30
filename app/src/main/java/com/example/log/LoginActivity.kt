package com.example.log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        // Initialize Firebase Auth
        auth = Firebase.auth


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val registertext: TextView =findViewById(R.id.register_now)
        registertext.setOnClickListener{
            val intent = Intent(this,RegiterActivity::class.java)
            startActivity(intent)
        }
        val loginButton:Button = findViewById(R.id.login)
        loginButton.setOnClickListener{
            performSignIn()
        }
    }

    private fun performSignIn() {
        val email: EditText = findViewById<EditText>(R.id.username)
        val password:EditText = findViewById<EditText>(R.id.password)

        if (email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()
        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Authentication success.",
                        Toast.LENGTH_SHORT).show()


                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(this,"Error occurred${it.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
    }
}