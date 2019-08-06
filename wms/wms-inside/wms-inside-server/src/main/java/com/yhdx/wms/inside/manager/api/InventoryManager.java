package com.yhdx.wms.inside.manager.api;



import com.yhdx.wms.framework.model.tuples.Pair;
import com.yhdx.wms.inside.domain.Inventory;
import com.yhdx.wms.inside.domain.InventoryGetRequest;
import com.yhdx.wms.inside.domain.InventoryListRequest;
import com.yhdx.wms.inside.domain.InventoryLog;

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
