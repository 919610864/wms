package com.yhdx.wms.inbound.server.dao.mapper;


import com.yhdx.wms.inbound.server.domain.po.PurchaseOrderGoodsPO;

import java.util.Collection;
import java.util.List;

public interface PurchaseOrderGoodsMapper {

    int saveAllPurchaseOrderGoods(Collection<PurchaseOrderGoodsPO> collection);

    List<PurchaseOrderGoodsPO> listPurchaseOrderGoodsByOrderId(Long orderId);

}
