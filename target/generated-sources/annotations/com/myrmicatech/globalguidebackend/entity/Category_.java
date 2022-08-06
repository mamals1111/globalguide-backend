package com.myrmicatech.globalguidebackend.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ extends com.myrmicatech.globalguidebackend.entity.BaseEntity_ {

	public static volatile SingularAttribute<Category, String> name;
	public static volatile SingularAttribute<Category, String> description;
	public static volatile ListAttribute<Category, Location> locations;
	public static volatile SingularAttribute<Category, String> iconURL;

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String LOCATIONS = "locations";
	public static final String ICON_UR_L = "iconURL";

}

