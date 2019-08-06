package com.yhdx.wms.inside.server.manager.impl;


import com.yhdx.wms.framework.aop.DuplicateKeyCheck;
import com.yhdx.wms.framework.model.tuples.Pair;
import com.yhdx.wms.inside.server.dao.mapper.InventoryLogMapper;
import com.yhdx.wms.inside.server.dao.mapper.InventoryMapper;
import com.yhdx.wms.inside.server.domain.Inventory;
import com.yhdx.wms.inside.domain.InventoryGetRequest;
import com.yhdx.wms.inside.domain.InventoryListRequest;
import com.yhdx.wms.inside.server.domain.InventoryLog;
import com.yhdx.wms.inside.server.domain.converter.InventoryConverter;
import com.yhdx.wms.inside.server.domain.converter.InventoryLogConverter;
import com.yhdx.wms.inside.server.domain.po.InventoryPO;
import com.yhdx.wms.inside.server.manager.api.InventoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class InventoryManagerImpl implements InventoryManager {

    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private InventoryLogMapper inventoryLogDao;

    @DuplicateKeyCheck
    @Override
    public int saveInventory(Inventory inventory, InventoryLog inventoryLog) {
        int affectedRows = inventoryMapper.saveInventory(InventoryConverter.toPO(inventory));
        System.out.println("save affectedRows=" + affectedRows);
        if(affectedRows > 0) {
            inventoryLogDao.saveInventoryLog(InventoryLogConverter.toPO(inventoryLog));
        }
        return affectedRows;
    }

    @DuplicateKeyCheck
    @Override
    public int saveAllInventory(Collection<Inventory> inventoryCollection,
                                Collection<InventoryLog> inventoryLogCollection) {
        int affectedRows = inventoryMapper.saveAllInventory(InventoryConverter.toPO(inventoryCollection));
        if(affectedRows > 0) {
            inventoryLogDao.saveAllInventoryLog(InventoryLogConverter.toPO(inventoryLogCollection));
        }
        return affectedRows;
    }

    @Override
    public int updateInventory(Inventory before, Inventory after, InventoryLog inventoryLog) {
        int affectedRows =  inventoryMapper.updateInventory(InventoryConverter.toPO(before),
                InventoryConverter.toPO(after));
        System.out.println("update affectedRows=" + affectedRows);
        if(affectedRows > 0) {
            System.out.println("2 update affectedRows=" + inventoryLogDao.saveInventoryLog(InventoryLogConverter.toPO(inventoryLog)));
        }
        return affectedRows;
    }

    @Override
    public int updateAllInventory(Collection<Pair<Inventory, Inventory>> inventoryCollection,
                                  Collection<InventoryLog> inventoryLogCollection) {
        List<Pair<InventoryPO, InventoryPO>> inventoryPOList = new ArrayList<>();
        for(Pair<Inventory, Inventory> pair : inventoryCollection) {
            inventoryPOList.add(new Pair<>(InventoryConverter.toPO(pair.getItem1()),
                    InventoryConverter.toPO(pair.getItem2())));
        }
        int affectedRows =  inventoryMapper.updateAllInventory(inventoryPOList);
        if(affectedRows > 0) {
            inventoryLogDao.saveAllInventoryLog(InventoryLogConverter.toPO(inventoryLogCollection));
        }
        return affectedRows;
    }

    @Override
    public Inventory getInventory(InventoryGetRequest request) {
        return InventoryConverter.fromPO(inventoryMapper.getInventory(request));
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Inventory getInventoryWithoutTransaction(InventoryGetRequest request) {
        return InventoryConverter.fromPO(inventoryMapper.getInventory(request));
    }

    @Override
    public List<Inventory> listInventory(InventoryListRequest request) {
        return InventoryConverter.fromPO(inventoryMapper.listInventory(request));
    }

}
