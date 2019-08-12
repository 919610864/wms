package com.mglj.wms.inbound.server.domain.converter;

import com.mglj.wms.framework.util.BeanUtilsEx;
import com.mglj.wms.inbound.server.domain.PurchaseOrder;
import com.mglj.wms.inbound.server.domain.po.PurchaseOrderPO;


import java.util.Collection;
import java.util.List;

public class PurchaseOrderConverter {

    public final static PurchaseOrderPO toPO(PurchaseOrder source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderPO::new);
    }

    public final static List<PurchaseOrderPO> toPO(Collection<PurchaseOrder> source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderPO::new);
    }

    public final static PurchaseOrder fromPO(PurchaseOrderPO source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrder::new, false);
    }

    public final static List<PurchaseOrder> fromPO(Collection<PurchaseOrderPO> source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrder::new, false);
    }

}
