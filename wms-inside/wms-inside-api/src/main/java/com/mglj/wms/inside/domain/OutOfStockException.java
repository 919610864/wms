package com.mglj.wms.inside.domain;

public class OutOfStockException extends InventoryException {

    public OutOfStockException() {
        super();
    }

    public OutOfStockException(String message) {
        super(message);
    }

}
