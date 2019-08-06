package com.yhdx.wms.inbound.server.dao.mapper;

import com.yhdx.wms.inbound.server.domain.po.PurchaseOrderPO;


public interface PurchaseOrderMapper {

    int savePurchaseOrder(PurchaseOrderPO purchaseOrderPO);

    PurchaseOrderPO getPurchaseOrder(Long id);

}
