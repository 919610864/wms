package com.yhdx.wms.inbound.dao.mapper;


import com.yhdx.wms.inbound.domain.po.PurchaseOrderGoodsPO;

import java.util.Collection;
import java.util.List;

public interface PurchaseOrderGoodsMapper {

    int saveAllPurchaseOrderGoods(Collection<PurchaseOrderGoodsPO> collection);

    List<PurchaseOrderGoodsPO> listPurchaseOrderGoodsByOrderId(Long orderId);

}
