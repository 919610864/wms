package com.yhdx.wms.inbound.manager.impl;

import com.yhdx.wms.inbound.dao.mapper.PurchaseOrderAllocationMapper;
import com.yhdx.wms.inbound.dao.mapper.PurchaseOrderGoodsMapper;
import com.yhdx.wms.inbound.dao.mapper.PurchaseOrderMapper;
import com.yhdx.wms.inbound.domain.PurchaseOrder;
import com.yhdx.wms.inbound.domain.PurchaseOrderAllocation;
import com.yhdx.wms.inbound.domain.converter.PurchaseOrderAllocationConverter;
import com.yhdx.wms.inbound.domain.converter.PurchaseOrderConverter;
import com.yhdx.wms.inbound.domain.converter.PurchaseOrderGoodsConverter;
import com.yhdx.wms.inbound.domain.po.PurchaseOrderGoodsPO;
import com.yhdx.wms.inbound.domain.po.PurchaseOrderPO;
import com.yhdx.wms.inbound.manager.api.PurchaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;

@Service
public class PurchaseManagerImpl implements PurchaseManager {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private PurchaseOrderGoodsMapper purchaseOrderGoodsMapper;
    @Autowired
    private PurchaseOrderAllocationMapper purchaseOrderAllocationDao;

    @Override
    public int savePurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderPO purchaseOrderPO = PurchaseOrderConverter.toPO(purchaseOrder);
        int affectedRows = purchaseOrderMapper.savePurchaseOrder(purchaseOrderPO);
        if(affectedRows > 0) {
            List<PurchaseOrderGoodsPO> purchaseOrderGoodsPOList = PurchaseOrderGoodsConverter.toPO(purchaseOrder.getGoodsList());
            for(PurchaseOrderGoodsPO purchaseOrderGoodsPO : purchaseOrderGoodsPOList) {
                purchaseOrderGoodsPO.setOrderId(purchaseOrderPO.getId());
            }
            purchaseOrderGoodsMapper.saveAllPurchaseOrderGoods(purchaseOrderGoodsPOList);
        }
        return affectedRows;
    }

    @Override
    public PurchaseOrder getPurchaseOrder(Long id) {
        PurchaseOrder purchaseOrder = PurchaseOrderConverter.fromPO(purchaseOrderMapper.getPurchaseOrder(id));
        if(purchaseOrder != null) {
            purchaseOrder.setGoodsList(PurchaseOrderGoodsConverter.fromPO(
                    purchaseOrderGoodsMapper.listPurchaseOrderGoodsByOrderId(id)));
        }
        return purchaseOrder;
    }

    @Override
    public int saveAllPurchaseOrderAllocation(Collection<PurchaseOrderAllocation> collection) {
        return purchaseOrderAllocationDao.saveAllPurchaseOrderAllocation(
                PurchaseOrderAllocationConverter.toPO(collection));
    }

}
