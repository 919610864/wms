package com.yhdx.wms.inside.feign.api;

import com.yhdx.wms.framework.model.Result;
import com.yhdx.wms.framework.model.tuples.Triple;
import com.yhdx.wms.inside.domain.InventoryChangeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;

@FeignClient(value = "${wms-inventory-service.name}")
public interface InventoryClient {

    @PostMapping("inventory/update")
    Result<?> updateInvertory(InventoryChangeRequest request);

    @PostMapping("inventory/update-all")
    Result<?> updateAllInvertory(Collection<InventoryChangeRequest> collection);

    @GetMapping("inventory/available-location")
    Result<List<Triple<Long, Long, Long>>> listAvailableLocation();

}
