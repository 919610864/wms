package com.yhdx.wms.inbound.server.dao.mapper;


import com.yhdx.wms.inbound.server.domain.po.PurchaseOrderAllocationPO;

import java.util.Collection;

public interface PurchaseOrderAllocationMapper {

    int saveAllPurchaseOrderAllocation(Collection<PurchaseOrderAllocationPO> collection);

}
