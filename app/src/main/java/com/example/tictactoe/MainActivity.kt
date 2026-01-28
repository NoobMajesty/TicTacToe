package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : ComponentActivity() {

    private lateinit var gameManager: GameController
    private lateinit var buttons: Array<Array<Button>>
    private lateinit var statusText: TextView
    private lateinit var newGameBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tictactoe_main)

        gameManager = GameController()

        statusText = findViewById(R.id.statusText)
        newGameBtn = findViewById(R.id.newGameBtn)

        buttons = arrayOf(
            arrayOf(findViewById(R.id.btn00), findViewById(R.id.btn01), findViewById(R.id.btn02)),
            arrayOf(findViewById(R.id.btn10), findViewById(R.id.btn11), findViewById(R.id.btn12)),
            arrayOf(findViewById(R.id.btn20), findViewById(R.id.btn21), findViewById(R.id.btn22))
        )

        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].setOnClickListener {
                    onCellClicked(i, j)
                }
            }
        }

        newGameBtn.setOnClickListener { resetGame() }
    }

    private fun onCellClicked(row: Int, col: Int) {
        if (!gameManager.makeMove(row, col)) return

        buttons[row][col].text = gameManager.currentPlayer.name

        if (gameManager.checkWin()) {
            statusText.text = "${gameManager.currentPlayer} wins!"
            Toast.makeText(this, "${gameManager.currentPlayer} wins!", Toast.LENGTH_LONG).show()
            disableBoard()
        } else if (gameManager.isDraw()) {
            statusText.text = "Draw!"
            Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show()
        } else {
            gameManager.switchPlayer()
            statusText.text = "Player ${gameManager.currentPlayer.name}'s Turn"
        }
    }

    private fun disableBoard() {
        for (row in buttons)
            for (btn in row)
                btn.isEnabled = false
    }

    private fun resetGame() {
        gameManager.reset()
        for (row in buttons)
            for (btn in row) {
                btn.text = ""
                btn.isEnabled = true
            }
        statusText.text = "Player X's Turn"
    }
}