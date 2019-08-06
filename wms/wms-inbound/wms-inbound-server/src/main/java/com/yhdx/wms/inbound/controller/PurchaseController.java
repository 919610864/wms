package com.yhdx.wms.inbound.controller;

import com.yhdx.wms.framework.model.Result;
import com.yhdx.wms.inbound.domain.SavePurchaseOrderRequest;
import com.yhdx.wms.inbound.service.api.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("purchase-order/save")
    public Result<?> savePurchaseOrder(@RequestBody SavePurchaseOrderRequest request) {
        purchaseService.savePurchaseOrder(request);
        return Result.ok();
    }

    @RequestMapping("purchase-order/shelve")
    public Result<?> shelvePurchaseOrder(Long purchaseOrderId) {
        purchaseService.shelvePurchaseOrder(purchaseOrderId);
        return Result.ok();
    }

}
