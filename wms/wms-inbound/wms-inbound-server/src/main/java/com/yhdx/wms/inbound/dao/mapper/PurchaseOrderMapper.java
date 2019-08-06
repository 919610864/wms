package com.yhdx.wms.inbound.dao.mapper;

import com.yhdx.wms.inbound.domain.po.PurchaseOrderPO;


public interface PurchaseOrderMapper {

    int savePurchaseOrder(PurchaseOrderPO purchaseOrderPO);

    PurchaseOrderPO getPurchaseOrder(Long id);

}
