package com.myrmicatech.globalguidebackend.entity.ids;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountLocationId.class)
public abstract class AccountLocationId_ {

	public static volatile SingularAttribute<AccountLocationId, UUID> locationId;
	public static volatile SingularAttribute<AccountLocationId, UUID> userId;

	public static final String LOCATION_ID = "locationId";
	public static final String USER_ID = "userId";

}

