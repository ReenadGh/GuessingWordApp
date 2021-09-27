package com.example.myapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var input: EditText
    lateinit var buttonGuess: Button
    lateinit var phasesText: TextView
    val massages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.taskinput)
        buttonGuess = findViewById(R.id.button3)
        phasesText = findViewById(R.id.phasesText)
        val myRV = findViewById<RecyclerView>(R.id.rvMain)
        myRV.adapter = RecyclerViewAdapter(massages)
        myRV.layoutManager = LinearLayoutManager(this)
        myRV.adapter!!.notifyDataSetChanged()


        val listOfWord = arrayListOf<String>("cat", "Word", "end", "letter")

        var Gessword = listOfWord[Random.nextInt(0, listOfWord.size)]
        var wordHint = ""
        var hintCharIndex = Random.nextInt(0, Gessword.length)
        var hintChar = Gessword[hintCharIndex]
        var numOfGusses = 3;

        for (i in Gessword.indices) {
            wordHint += '*'
        }
        wordHint =
            wordHint.substring(0, hintCharIndex) + hintChar + wordHint.substring(hintCharIndex + 1)

        phasesText.text= "Gess this Word : " + wordHint



        buttonGuess.setOnClickListener {


            var UserGuessWord = input.text.toString()


            if (UserGuessWord.isEmpty()) {
                Snackbar.make(myRV, "Enter a Word !", Snackbar.LENGTH_SHORT).show()
            } else {

                if (UserGuessWord == Gessword) {
                    massages.add("Right !")
                    showAlertDialog("Right its ($Gessword ), play again ?")
                } else if (numOfGusses == 0) {
                    showAlertDialog("Wrong the word was : $Gessword , play again ?")

                } else {
                    massages.add("Wrong try again ! ")
                    numOfGusses--

                }
            }


        }

    }


    private fun showAlertDialog(title: String) {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage(title)
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game Over")
        // show alert dialog
        alert.show()
    }
}












