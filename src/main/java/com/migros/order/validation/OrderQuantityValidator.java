package com.migros.order.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderQuantityValidator implements ConstraintValidator<ValidOrderQuantity, String> {

	private int minQuantity;

	@Override
	public void initialize(ValidOrderQuantity validOrderQuantity) {

		this.minQuantity = validOrderQuantity.min();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isNotEmpty(value)) {
			try {
				int currentQuatity = Integer.parseInt(value);
				
				if(currentQuatity>=minQuantity)
				{
					return true;
				}
				
				
			} catch (Exception e) {
				
				return false;
			}

		}

		return false;
	}

}
