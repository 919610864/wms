package com.mglj.wms.inbound.server.dao.mapper;

import com.mglj.wms.inbound.server.domain.po.PurchaseOrderPO;
import org.springframework.stereotype.Component;

@Component
public interface PurchaseOrderMapper {

    int savePurchaseOrder(PurchaseOrderPO purchaseOrderPO);

    PurchaseOrderPO getPurchaseOrder(Long id);

}
