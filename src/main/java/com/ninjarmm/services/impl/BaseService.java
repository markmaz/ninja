package com.ninjarmm.services.impl;


import com.ninjarmm.exceptions.UnprocessableEntityException;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class BaseService<T> {
    private final Validator validator;

    public BaseService(final Validator validator) {
        this.validator = validator;
    }

    protected void validate(T item)
    {
        var violations = validator.validate(item);

        if (!violations.isEmpty())
            throw new UnprocessableEntityException(violations.toString());
    }
}
