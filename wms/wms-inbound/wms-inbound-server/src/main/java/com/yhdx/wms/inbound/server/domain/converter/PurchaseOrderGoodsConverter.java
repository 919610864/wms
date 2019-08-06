package com.yhdx.wms.inbound.server.domain.converter;

import com.yhdx.wms.framework.util.BeanUtilsEx;
import com.yhdx.wms.inbound.server.domain.PurchaseOrderGoods;
import com.yhdx.wms.inbound.server.domain.po.PurchaseOrderGoodsPO;


import java.util.Collection;
import java.util.List;

public class PurchaseOrderGoodsConverter {

    public final static PurchaseOrderGoodsPO toPO(PurchaseOrderGoods source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderGoodsPO::new);
    }

    public final static List<PurchaseOrderGoodsPO> toPO(Collection<PurchaseOrderGoods> source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderGoodsPO::new);
    }

    public final static PurchaseOrderGoods fromPO(PurchaseOrderGoodsPO source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderGoods::new, false);
    }

    public final static List<PurchaseOrderGoods> fromPO(Collection<PurchaseOrderGoodsPO> source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderGoods::new, false);
    }

}
