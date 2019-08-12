package com.mglj.wms.inside.server.domain.converter;


import com.mglj.wms.framework.util.BeanUtilsEx;
import com.mglj.wms.inside.server.domain.InventoryLog;
import com.mglj.wms.inside.server.domain.po.InventoryLogPO;


import java.util.Collection;
import java.util.List;

public class InventoryLogConverter {

    public static InventoryLogPO toPO(InventoryLog source) {
        return BeanUtilsEx.copyProperties(source, InventoryLogPO::new);
    }

    public static List<InventoryLogPO> toPO(Collection<InventoryLog> source) {
        return BeanUtilsEx.copyProperties(source, InventoryLogPO::new);
    }

}
