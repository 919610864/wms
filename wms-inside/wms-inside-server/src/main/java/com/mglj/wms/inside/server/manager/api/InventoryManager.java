package com.mglj.wms.inside.server.manager.api;



import com.mglj.wms.framework.model.tuples.Pair;
import com.mglj.wms.inside.server.domain.Inventory;
import com.mglj.wms.inside.domain.InventoryGetRequest;
import com.mglj.wms.inside.domain.InventoryListRequest;
import com.mglj.wms.inside.server.domain.InventoryLog;

import java.util.Collection;
import java.util.List;

public interface InventoryManager {

    int saveInventory(Inventory inventory, InventoryLog inventoryLog);

    int saveAllInventory(Collection<Inventory> inventoryCollection,
                         Collection<InventoryLog> inventoryLogCollection);

    int updateInventory(Inventory before, Inventory after, InventoryLog inventoryLog);

    int updateAllInventory(Collection<Pair<Inventory, Inventory>> inventoryCollection,
                           Collection<InventoryLog> inventoryLogCollection);

    Inventory getInventory(InventoryGetRequest request);

    Inventory getInventoryWithoutTransaction(InventoryGetRequest request);

    List<Inventory> listInventory(InventoryListRequest request);

}
