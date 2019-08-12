package com.mglj.wms.inside.server.domain.converter;


import com.mglj.wms.framework.util.BeanUtilsEx;
import com.mglj.wms.inside.server.domain.Inventory;
import com.mglj.wms.inside.server.domain.po.InventoryPO;

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
