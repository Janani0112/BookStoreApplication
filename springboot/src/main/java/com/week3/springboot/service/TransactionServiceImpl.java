package com.week3.springboot.service;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.week3.springboot.model.Book;
import com.week3.springboot.model.Inventory;
import com.week3.springboot.model.Transaction;
import com.week3.springboot.model.User;
import com.week3.springboot.model.Wallet;
import com.week3.springboot.repository.BookRepository;
import com.week3.springboot.repository.InventoryRepository;
import com.week3.springboot.repository.TransactionRepository;
import com.week3.springboot.repository.UserRepository;
import com.week3.springboot.repository.WalletRepository;

@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionrepo;
    @Autowired
    private WalletRepository walletrepo;
    @Autowired
    BookRepository bookrepo;
    @Autowired
    private UserRepository userrepo;
    @Autowired
    Wallet walletUpdated;
    @Autowired
    Book bookAssigned;
    @Autowired
    private InventoryRepository invrepo;

    public TransactionServiceImpl() {
    }

    public Transaction createTransactionBorrow(int classId, Transaction transaction) {
        List<Book> BookList = this.transactionrepo.findBookByClassId(classId);
        System.out.println("List of Books : " + BookList);
        boolean bookSet = false;
        Iterator var6 = BookList.iterator();

        while(var6.hasNext()) {
            Book book = (Book)var6.next();
            if (!book.isBookStatus()) {
                System.out.println("Status of Book " + book.getBookId() + book.isBookStatus());
                bookSet = true;
                transaction.setBookId(book.getBookId());
                this.bookAssigned = book;
                break;
            }
        }

        if (!bookSet) {
            System.out.println("Book is out of stock");
            return null;
        } else {
            System.out.println("Assigned Book :" + this.bookAssigned);
            System.out.println(transaction.getUserId());
            Optional<User> user = this.userrepo.findById(transaction.getUserId());
            System.out.println(user.isPresent());
            if (user.isPresent()) {
                User userObj = (User)user.get();
                System.out.println(userObj.isUserStatus());
                transaction.setBookId(this.bookAssigned.getBookId());
                int amount = this.transactionrepo.findWalletBalanceByUserId(userObj.getUserId());
                Inventory inv = this.transactionrepo.findInventoryByClassId(this.transactionrepo.findClassIdByBookId(transaction.getBookId()));
                List<Transaction> transRecord = this.transactionrepo.findTransactionByUserId(userObj.getUserId());
                if (transRecord.size() > 2) {
                    System.out.println("Limit is Exceeded " + userObj.getUserName() + " Return for next borrow");
                    return null;
                }

                Iterator var11 = transRecord.iterator();

                while(var11.hasNext()) {
                    Transaction trans = (Transaction)var11.next();
                    int prevClassId = this.transactionrepo.findClassIdByBookId(trans.getBookId());
                    if (trans.getDateReturned() == null && this.transactionrepo.findClassIdByBookId(transaction.getBookId()) == prevClassId) {
                        System.out.println("cannot borrow same book again");
                        return null;
                    }
                }

                if (userObj.isUserStatus() && (double)amount >= 0.2 * (double)inv.getBookPrice()) {
                    if (inv.getBookCount() == 0) {
                        System.out.println("Book out of stock");
                        return null;
                    }

                    transaction.setDeposit((int)(0.2 * (double)inv.getBookPrice()));
                    transaction.setRefundBalance((int)(0.1 * (double)inv.getBookPrice()));
                    Wallet userWallet = this.transactionrepo.findWalletByUserId(userObj.getUserId());
                    this.walletUpdated.setBalance(amount - transaction.getDeposit());
                    this.walletUpdated.setWalletId(userWallet.getWalletId());
                    this.walletUpdated.setUserId(userWallet.getUserId());
                    this.walletrepo.save(this.walletUpdated);
                    inv.setBookCount(inv.getBookCount() - 1);
                    this.invrepo.save(inv);
                    this.bookAssigned.setBookStatus(true);
                    this.bookrepo.save(this.bookAssigned);
                    Transaction transactionAdded = (Transaction)this.transactionrepo.save(transaction);
                    return transactionAdded;
                }

                if ((double)amount < 0.2 * (double)inv.getBookPrice()) {
                    System.out.println("Please Recharge your wallet " + userObj.getUserName());
                } else {
                    System.out.println("User" + userObj.getUserId() + "Suspended User");
                }
            }

            return null;
        }
    }

    public List<Transaction> getUserTransactions(int userId) {
        return this.transactionrepo.findTransactionByUserId(userId);
    }
}
