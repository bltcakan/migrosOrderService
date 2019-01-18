package com.migros.order.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class OrderDateValidator implements ConstraintValidator<ValidOrderDate, String> {

	Date currentDate;

	@Override
	public void initialize(ValidOrderDate validOrderDate) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isNotEmpty(value)) {

			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime orderTime = LocalDateTime.parse(value, formatter);
				LocalDateTime now = LocalDateTime.now();

				if (orderTime.isAfter(now)) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {

				return false;
			}
		}

		return false;
	}

}
