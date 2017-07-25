package com.coffee.shop.dao.exception;

/**
 * @author Lokki17
 * @since 17.03.2017
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
