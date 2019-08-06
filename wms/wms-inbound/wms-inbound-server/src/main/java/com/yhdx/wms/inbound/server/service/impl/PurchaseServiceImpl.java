package com.yhdx.wms.inbound.server.service.impl;

import com.yhdx.wms.framework.model.tuples.Triple;
import com.yhdx.wms.inbound.server.domain.PurchaseOrder;
import com.yhdx.wms.inbound.server.domain.PurchaseOrderAllocation;
import com.yhdx.wms.inbound.server.domain.PurchaseOrderGoods;
import com.yhdx.wms.inbound.server.domain.SavePurchaseOrderRequest;
import com.yhdx.wms.inbound.server.manager.api.PurchaseManager;
import com.yhdx.wms.inbound.server.service.api.PurchaseService;
import com.yhdx.wms.inside.domain.InventoryChangeRequest;
import com.yhdx.wms.inside.domain.InventoryId;
import com.yhdx.wms.inside.domain.InventoryOperationTypeEnum;
import com.yhdx.wms.inside.feign.api.InventoryClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseManager purchaseManager;

    @Autowired
    private InventoryClient inventoryClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void savePurchaseOrder(SavePurchaseOrderRequest savePurchaseOrderRequest) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCreationTime(LocalDateTime.now());
        List<PurchaseOrderGoods> goodsList = new ArrayList<>();
        purchaseOrder.setGoodsList(goodsList);
        for(SavePurchaseOrderRequest.Goods goods : savePurchaseOrderRequest.getGoodsList()) {
            PurchaseOrderGoods purchaseOrderGoods = new PurchaseOrderGoods();
            goodsList.add(purchaseOrderGoods);
            purchaseOrderGoods.setGoodsId(goods.getGoodsId());
            purchaseOrderGoods.setCount(goods.getCount());
        }
        purchaseManager.savePurchaseOrder(purchaseOrder);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void shelvePurchaseOrder(Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = purchaseManager.getPurchaseOrder(purchaseOrderId);
        if(purchaseOrder == null) {
            return;
        }
        List<PurchaseOrderGoods> purchaseOrderGoodsList = purchaseOrder.getGoodsList();
        List<Triple<Long, Long, Long>> availableLocationList = inventoryClient.listAvailableLocation().getData();
        Random random = new Random();
        List<PurchaseOrderAllocation> purchaseOrderAllocationList = new ArrayList<>();
        List<InventoryChangeRequest> inventoryChangeRequestList = new ArrayList<>();
        for(PurchaseOrderGoods purchaseOrderGoods : purchaseOrderGoodsList) {
            PurchaseOrderAllocation purchaseOrderAllocation = new PurchaseOrderAllocation();
            purchaseOrderAllocation.setOrderId(purchaseOrder.getId());
            Triple<Long, Long, Long> location = availableLocationList.get(random.nextInt(availableLocationList.size()));
            purchaseOrderAllocation.setCoordX(location.getItem1());
            purchaseOrderAllocation.setCoordY(location.getItem2());
            purchaseOrderAllocation.setCoordZ(location.getItem3());
            purchaseOrderAllocation.setGoodsId(purchaseOrderGoods.getGoodsId());
            purchaseOrderAllocation.setCount(purchaseOrderGoods.getCount());
            purchaseOrderAllocationList.add(purchaseOrderAllocation);

            InventoryChangeRequest inventoryChangeRequest = new InventoryChangeRequest();
            inventoryChangeRequest.setOperationId(random.nextLong());
            inventoryChangeRequest.setBizId(purchaseOrder.getId());
            inventoryChangeRequest.setOperationType(InventoryOperationTypeEnum.ADDITION);
            inventoryChangeRequest.setInventoryId(new InventoryId(location.getItem1(),
                    location.getItem2(), location.getItem3(), purchaseOrderGoods.getGoodsId()));
            inventoryChangeRequest.setCount(purchaseOrderGoods.getCount());
            inventoryChangeRequestList.add(inventoryChangeRequest);
        }
        purchaseManager.saveAllPurchaseOrderAllocation(purchaseOrderAllocationList);
        inventoryClient.updateAllInvertory(inventoryChangeRequestList);
    }

}
