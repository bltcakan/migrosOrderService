package com.migros.order.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = com.migros.order.validation.OrderDateValidator.class)
@Documented
public @interface  ValidOrderDate {
	String message() default "order date must be after current date";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

 }
