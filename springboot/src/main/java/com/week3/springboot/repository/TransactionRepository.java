package com.week3.springboot.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.week3.springboot.model.Book;
import com.week3.springboot.model.Inventory;
import com.week3.springboot.model.Transaction;
import com.week3.springboot.model.Wallet;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
 @Query("Select t from Transaction t where t.userId=?1")
 List<Transaction> findTransactionByUserId(int var1);

 @Query("Select t.balance from Wallet t where t.userId=?1")
 int findWalletBalanceByUserId(int var1);

 @Query("Select t from Wallet t where t.userId=?1")
 Wallet findWalletByUserId(int var1);

 @Query("Select b.classId from Book b where b.bookId=?1")
 int findClassIdByBookId(int var1);

 @Query("Select i from Inventory i where i.classId=?1")
 Inventory findInventoryByClassId(int var1);

 @Query("Select b.bookId from Book b where b.classId=?1")
 int findBookIdByClassId(int var1);

 @Query("Select b from Book b where b.classId=?1")
 List<Book> findBookByClassId(int var1);
}
