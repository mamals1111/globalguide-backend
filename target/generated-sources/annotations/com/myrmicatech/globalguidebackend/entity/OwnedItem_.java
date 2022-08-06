package com.myrmicatech.globalguidebackend.entity;

import com.myrmicatech.globalguidebackend.entity.ids.AccountItemId;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OwnedItem.class)
public abstract class OwnedItem_ {

	public static volatile SingularAttribute<OwnedItem, Item> item;
	public static volatile ListAttribute<OwnedItem, Review> reviews;
	public static volatile SingularAttribute<OwnedItem, Integer> level;
	public static volatile SingularAttribute<OwnedItem, Double> price;
	public static volatile SingularAttribute<OwnedItem, AccountItemId> accountItemId;
	public static volatile SingularAttribute<OwnedItem, UUID> hashId;
	public static volatile SingularAttribute<OwnedItem, Account> account;
	public static volatile SingularAttribute<OwnedItem, Integer> energy;

	public static final String ITEM = "item";
	public static final String REVIEWS = "reviews";
	public static final String LEVEL = "level";
	public static final String PRICE = "price";
	public static final String ACCOUNT_ITEM_ID = "accountItemId";
	public static final String HASH_ID = "hashId";
	public static final String ACCOUNT = "account";
	public static final String ENERGY = "energy";

}

