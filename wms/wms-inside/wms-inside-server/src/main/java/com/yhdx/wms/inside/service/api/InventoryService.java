package com.yhdx.wms.inside.service.api;



import com.yhdx.wms.inside.domain.InventoryChangeRequest;

import java.util.Collection;

public interface InventoryService {

    void updateInventory(InventoryChangeRequest request);

    void updateInventory(Collection<InventoryChangeRequest> collection);

}
