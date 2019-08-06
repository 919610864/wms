package com.yhdx.wms.inside.server.dao.mapper;


import com.yhdx.wms.inside.server.domain.po.InventoryLogPO;

import java.util.Collection;

/**
*  @author author
*/
public interface InventoryLogMapper {

    int saveInventoryLog(InventoryLogPO object);

    int saveAllInventoryLog(Collection<InventoryLogPO> collection);

}