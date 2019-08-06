package com.yhdx.wms.inbound.server.domain.po;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderGoodsPO {

    private Long id;
    private Long OrderId;
    private Long goodsId;
    private Integer count;

}
