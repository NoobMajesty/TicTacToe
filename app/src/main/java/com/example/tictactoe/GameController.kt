package com.example.tictactoe

class GameController {

    var currentPlayer = Player.X
        private set

    private var board = Array(3) { arrayOfNulls<Player>(3) }

    fun makeMove(row: Int, col: Int): Boolean {
        if (board[row][col] != null) return false

        board[row][col] = currentPlayer
        return true
    }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }

    fun checkWin(): Boolean {
        //  columns
        for (i in 0..2) {
            if (board[i][0] != null &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]
            ) return true
        }

        // rows
        for (i in 0..2) {

            if (board[0][i] != null &&
                board[0][i] == board[1][i] &&
                board[1][i] == board[2][i]
            ) return true
        }

        // diagonals
        if (board[0][0] != null &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]
        ) return true

        if (board[0][2] != null &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]
        ) return true

        return false
    }

    fun isDraw(): Boolean {
        return board.all { row -> row.all { it != null } }
    }

    fun reset() {
        board = Array(3) { arrayOfNulls<Player>(3) }
        currentPlayer = Player.X
    }
}