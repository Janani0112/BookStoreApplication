package com.week3.springboot.service;


import java.util.Iterator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.week3.springboot.model.Book;
import com.week3.springboot.model.Inventory;
import com.week3.springboot.repository.BookRepository;
import com.week3.springboot.repository.InventoryRepository;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryrepo;
    @Autowired
    private BookRepository bookrepo;

    public InventoryServiceImpl() {
    }

    public Inventory createInventory(Inventory inv) {
        Inventory inventoryAdded = (Inventory)this.inventoryrepo.save(inv);

        for(int i = 0; i < inventoryAdded.getBookCount(); ++i) {
            Book book = new Book();
            book.setClassId(inventoryAdded.getClassId());
            this.bookrepo.save(book);
            System.out.println(book);
        }

        return inventoryAdded;
    }

    public List<Inventory> getInventories() {
        return this.inventoryrepo.findAll();
    }

    public List<Inventory> getInventoryByBookName(String bookName) {
        List<Inventory> bookList = this.inventoryrepo.findInventoryByBookName(bookName);
        Iterator var4 = bookList.iterator();

        while(var4.hasNext()) {
            Inventory inventory = (Inventory)var4.next();
            if (inventory.getBookCount() == 0) {
                System.out.println("BOOK " + inventory.getClassId() + " OUT OF STOCK");
            }
        }

        return bookList;
    }

    public List<Inventory> getSortedInventoryByBookLikes() {
        return this.inventoryrepo.findSortedInventoryByBookLikes();
    }
}
