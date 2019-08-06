package com.yhdx.wms.inbound.server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderGoods {

    private Long id;
    private Long OrderId;
    private Long goodsId;
    private Integer count;

}
