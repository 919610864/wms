package com.yhdx.wms.inside.server.domain.converter;


import com.yhdx.wms.framework.util.BeanUtilsEx;
import com.yhdx.wms.inside.server.domain.Inventory;
import com.yhdx.wms.inside.server.domain.po.InventoryPO;

import java.util.Collection;
import java.util.List;

public class InventoryConverter {

    public static InventoryPO toPO(Inventory source) {
        return BeanUtilsEx.copyProperties(source, InventoryPO::new);
    }

    public static List<InventoryPO> toPO(Collection<Inventory> source) {
        return BeanUtilsEx.copyProperties(source, InventoryPO::new);
    }

    public static Inventory fromPO(InventoryPO source) {
        return BeanUtilsEx.copyProperties(source, Inventory::new, false);
    }

    public static List<Inventory> fromPO(Collection<InventoryPO> source) {
        return BeanUtilsEx.copyProperties(source, Inventory::new, false);
    }

}
