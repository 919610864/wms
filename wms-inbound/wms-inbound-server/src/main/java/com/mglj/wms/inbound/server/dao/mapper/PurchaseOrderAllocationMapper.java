package com.mglj.wms.inbound.server.dao.mapper;


import com.mglj.wms.inbound.server.domain.po.PurchaseOrderAllocationPO;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface PurchaseOrderAllocationMapper {

    int saveAllPurchaseOrderAllocation(Collection<PurchaseOrderAllocationPO> collection);

}
