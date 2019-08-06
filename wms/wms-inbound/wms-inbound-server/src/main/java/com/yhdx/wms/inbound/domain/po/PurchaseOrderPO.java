package com.yhdx.wms.inbound.domain.po;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseOrderPO {

    private Long id;
    private LocalDateTime creationTime;

}
