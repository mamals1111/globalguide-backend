package com.myrmicatech.globalguidebackend.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ {

	public static volatile SingularAttribute<BaseEntity, LocalDateTime> createdDate;
	public static volatile SingularAttribute<BaseEntity, Boolean> isDeleted;
	public static volatile SingularAttribute<BaseEntity, LocalDateTime> deletedDate;
	public static volatile SingularAttribute<BaseEntity, UUID> id;
	public static volatile SingularAttribute<BaseEntity, Boolean> isActive;
	public static volatile SingularAttribute<BaseEntity, LocalDateTime> updatedAt;

	public static final String CREATED_DATE = "createdDate";
	public static final String IS_DELETED = "isDeleted";
	public static final String DELETED_DATE = "deletedDate";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String UPDATED_AT = "updatedAt";

}

