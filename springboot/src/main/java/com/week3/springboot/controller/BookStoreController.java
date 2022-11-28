package com.week3.springboot.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.week3.springboot.model.Inventory;
import com.week3.springboot.service.InventoryService;

@RestController
public class BookStoreController {
    @Autowired
    private InventoryService invService;

    public BookStoreController() {
    }

    @GetMapping({"/allbooks"})
    private ResponseEntity<List<Inventory>> getAllInventories() {
        return ResponseEntity.ok().body(this.invService.getInventories());
    }

    @PostMapping({"/addbooks"})
    private ResponseEntity<Inventory> saveInventories(@RequestBody Inventory inv) {
        try {
            return ResponseEntity.ok().body(this.invService.createInventory(inv));
        } catch (Exception var3) {
            System.out.println("Check the inputs... " + var3.getStackTrace());
            return null;
        }
    }

    @GetMapping({"/books/{bookName}"})
    private ResponseEntity<List<Inventory>> getInventoryByBookName(@PathVariable String bookName) {
        return ResponseEntity.ok().body(this.invService.getInventoryByBookName(bookName));
    }

    @GetMapping({"/books/sortbylikes"})
    private ResponseEntity<List<Inventory>> getSortedInventoryLikes() {
        return ResponseEntity.ok().body(this.invService.getSortedInventoryByBookLikes());
    }
}
