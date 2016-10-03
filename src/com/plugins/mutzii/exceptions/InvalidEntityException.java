package com.plugins.mutzii.exceptions;

import org.bukkit.entity.LivingEntity;

public class InvalidEntityException extends IllegalArgumentException {
	private static final long serialVersionUID = -1454685423522552612L;
	private final LivingEntity entity;

	public InvalidEntityException(String message, LivingEntity entity) {
		super(message);
		this.entity = entity;
	}
	
	/**
	 * @return the entity which you wanted to control
	 */
	public LivingEntity getInvalidEntity() {
		return this.entity;
	}

}