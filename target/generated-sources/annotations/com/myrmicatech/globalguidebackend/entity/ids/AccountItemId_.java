package com.myrmicatech.globalguidebackend.entity.ids;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountItemId.class)
public abstract class AccountItemId_ {

	public static volatile SingularAttribute<AccountItemId, UUID> itemId;
	public static volatile SingularAttribute<AccountItemId, UUID> userId;

	public static final String ITEM_ID = "itemId";
	public static final String USER_ID = "userId";

}

