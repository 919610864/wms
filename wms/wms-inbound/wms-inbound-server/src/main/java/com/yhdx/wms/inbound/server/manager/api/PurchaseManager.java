package com.yhdx.wms.inbound.server.manager.api;



import com.yhdx.wms.inbound.server.domain.PurchaseOrder;
import com.yhdx.wms.inbound.server.domain.PurchaseOrderAllocation;

import java.util.Collection;

public interface PurchaseManager {

    int savePurchaseOrder(PurchaseOrder purchaseOrder);

    PurchaseOrder getPurchaseOrder(Long id);

    int saveAllPurchaseOrderAllocation(Collection<PurchaseOrderAllocation> collection);

}
