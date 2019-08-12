package com.mglj.wms.inside.server.domain.po;


import com.mglj.wms.inside.domain.InventoryId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryPO {

    private InventoryId inventoryId;
    private Integer count;
    private Integer allocationCount;
    private LocalDateTime lastModificationTime;
}
