package com.yhdx.wms.inside.dao.mapper;


import com.yhdx.wms.inside.domain.po.InventoryLogPO;

import java.util.Collection;

/**
*  @author author
*/
public interface InventoryLogMapper {

    int saveInventoryLog(InventoryLogPO object);

    int saveAllInventoryLog(Collection<InventoryLogPO> collection);

}