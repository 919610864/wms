package com.yhdx.wms.inside.domain.po;


import com.yhdx.wms.inside.domain.InventoryId;
import com.yhdx.wms.inside.domain.InventoryOperationTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryLogPO {

    private Long id;
    private InventoryOperationTypeEnum operationType;
    private Long operationId;
    private Long bizId;
    private InventoryId inventoryId;
    private Integer count;
    private Integer allocationCount;
    private Integer changeCount;
    private LocalDateTime creationTime;
}
