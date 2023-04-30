package kz.kd.converterapp.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface TransactionDao {
    @Query("SELECT * FROM `Transaction`")
    fun getAllTransactions(): List<Transaction>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertTransaction(transaction: Transaction)

    @Query("DELETE FROM `Transaction` WHERE id = :id")
    fun deleteTransaction(id: Int)
}