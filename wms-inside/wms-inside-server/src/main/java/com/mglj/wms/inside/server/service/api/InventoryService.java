package com.mglj.wms.inside.server.service.api;



import com.mglj.wms.inside.domain.InventoryChangeRequest;

import java.util.Collection;

public interface InventoryService {

    void updateInventory(InventoryChangeRequest request);

    void updateInventory(Collection<InventoryChangeRequest> collection);

}
