package com.yhdx.wms.inside.server.domain;

import com.yhdx.wms.inside.domain.InventoryChangeRequest;
import com.yhdx.wms.inside.domain.InventoryId;
import com.yhdx.wms.inside.domain.InventoryOperationTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class InventoryLog {

    private Long id;
    private InventoryOperationTypeEnum operationType;
    private Long operationId;
    private Long bizId;
    private InventoryId inventoryId;
    private Integer count;
    private Integer allocationCount;
    private Integer changeCount;
    private LocalDateTime creationTime;

    public InventoryLog build(InventoryChangeRequest inventoryChangeRequest) {
        this.operationType = inventoryChangeRequest.getOperationType();
        this.operationId = inventoryChangeRequest.getOperationId();
        this.bizId = inventoryChangeRequest.getBizId();
        this.inventoryId = inventoryChangeRequest.getInventoryId();
        return this;
    }

    public InventoryLog build(Inventory inventory) {
        Integer count0;
        this.count = (count0 = inventory.getCount()) == null ? 0 : count0;
        this.allocationCount = (count0 = inventory.getAllocationCount()) == null ? 0 : count0;
        return this;
    }

    public InventoryLog build(Integer changeCount) {
        this.changeCount = changeCount == null ? 0 : changeCount;
        return this;
    }
}
