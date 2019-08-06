package com.yhdx.wms.inbound.server.service.api;


import com.yhdx.wms.inbound.server.domain.SavePurchaseOrderRequest;

public interface PurchaseService {

    /**
     * 保存采购单
     *
     * @param savePurchaseOrderRequest
     */
    void savePurchaseOrder(SavePurchaseOrderRequest savePurchaseOrderRequest);

    /**
     * 上架采购单
     *
     * @param purchaseOrderId
     */
    void shelvePurchaseOrder(Long purchaseOrderId);

}
