package com.myrmicatech.globalguidebackend.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Item.class)
public abstract class Item_ extends com.myrmicatech.globalguidebackend.entity.BaseEntity_ {

	public static volatile SingularAttribute<Item, Integer> defaultEnergy;
	public static volatile ListAttribute<Item, OwnedItem> ownedItems;
	public static volatile SingularAttribute<Item, String> name;
	public static volatile SingularAttribute<Item, Integer> defaultLevel;
	public static volatile SingularAttribute<Item, File> icon;
	public static volatile SingularAttribute<Item, File> photo;

	public static final String DEFAULT_ENERGY = "defaultEnergy";
	public static final String OWNED_ITEMS = "ownedItems";
	public static final String NAME = "name";
	public static final String DEFAULT_LEVEL = "defaultLevel";
	public static final String ICON = "icon";
	public static final String PHOTO = "photo";

}

