/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.setter;

/**
 * Defines setter ability for target object
 *
 * @param <T> target object class
 */
public interface Setter<T> {

	void accept(T model, Object value);

}