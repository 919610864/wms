package com.yhdx.wms.inbound.dao.mapper;


import com.yhdx.wms.inbound.domain.po.PurchaseOrderAllocationPO;

import java.util.Collection;

public interface PurchaseOrderAllocationMapper {

    int saveAllPurchaseOrderAllocation(Collection<PurchaseOrderAllocationPO> collection);

}
