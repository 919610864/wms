package com.yhdx.wms.inbound.server.controller.purchase;

import com.yhdx.wms.framework.model.Result;
import com.yhdx.wms.inbound.server.domain.SavePurchaseOrderRequest;
import com.yhdx.wms.inbound.server.service.api.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api("采购入库")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation(value="保存采购单", notes="保存采购单")
    @ApiImplicitParam(name = "request", value = "采购单实体", required = true, dataType = "SavePurchaseOrderRequest")
    @RequestMapping(value = "purchase-order/save", method = RequestMethod.POST)
    public Result<?> savePurchaseOrder(@RequestBody SavePurchaseOrderRequest request) {
        purchaseService.savePurchaseOrder(request);
        return Result.ok();
    }

    @ApiOperation(value="上架采购单", notes="上架采购单")
    @ApiImplicitParam(name = "purchaseOrderId", value = "采购单ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "purchase-order/shelve", method = RequestMethod.GET)
    public Result<?> shelvePurchaseOrder(Long purchaseOrderId) {
        purchaseService.shelvePurchaseOrder(purchaseOrderId);
        return Result.ok();
    }

}
