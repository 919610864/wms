package com.yhdx.wms.inside.server.dao.mapper;



import com.yhdx.wms.framework.model.tuples.Pair;
import com.yhdx.wms.inside.domain.InventoryGetRequest;
import com.yhdx.wms.inside.domain.InventoryListRequest;
import com.yhdx.wms.inside.server.domain.po.InventoryPO;

import java.util.Collection;
import java.util.List;

/**
*  @author author
*/
public interface InventoryMapper {

    int saveInventory(InventoryPO object);

    int saveAllInventory(Collection<InventoryPO> collection);

    int updateInventory(InventoryPO before, InventoryPO after);

    int updateAllInventory(Collection<Pair<InventoryPO, InventoryPO>> collection);

    InventoryPO getInventory(InventoryGetRequest request);

    List<InventoryPO> listInventory(InventoryListRequest request);

}