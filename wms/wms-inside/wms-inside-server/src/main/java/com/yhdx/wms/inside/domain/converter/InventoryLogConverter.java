package com.yhdx.wms.inside.domain.converter;


import com.yhdx.wms.framework.util.BeanUtilsEx;
import com.yhdx.wms.inside.domain.InventoryLog;
import com.yhdx.wms.inside.domain.po.InventoryLogPO;


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
