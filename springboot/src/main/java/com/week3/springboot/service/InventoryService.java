package com.week3.springboot.service;


import java.util.List;

import com.week3.springboot.model.Inventory;

public interface InventoryService {
    Inventory createInventory(Inventory var1);

    List<Inventory> getInventories();

    List<Inventory> getInventoryByBookName(String var1);

    List<Inventory> getSortedInventoryByBookLikes();
}
