package com.mglj.wms.inbound.server.dao.mapper;


import com.mglj.wms.inbound.server.domain.po.PurchaseOrderGoodsPO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public interface PurchaseOrderGoodsMapper {

    int saveAllPurchaseOrderGoods(Collection<PurchaseOrderGoodsPO> collection);

    List<PurchaseOrderGoodsPO> listPurchaseOrderGoodsByOrderId(Long orderId);

}
