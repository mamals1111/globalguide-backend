package com.myrmicatech.globalguidebackend.entity;

import com.myrmicatech.globalguidebackend.entity.ids.AccountLocationId;
import com.myrmicatech.globalguidebackend.enums.LocationLogType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LocationLog.class)
public abstract class LocationLog_ {

	public static volatile SingularAttribute<LocationLog, LocationLogType> locationLogType;
	public static volatile SingularAttribute<LocationLog, String> website;
	public static volatile SingularAttribute<LocationLog, String> address;
	public static volatile SingularAttribute<LocationLog, String> phoneNumber;
	public static volatile SingularAttribute<LocationLog, AccountLocationId> accountLocationId;
	public static volatile SingularAttribute<LocationLog, String> name;
	public static volatile SingularAttribute<LocationLog, String> description;
	public static volatile SingularAttribute<LocationLog, Location> location;
	public static volatile SingularAttribute<LocationLog, Account> account;

	public static final String LOCATION_LOG_TYPE = "locationLogType";
	public static final String WEBSITE = "website";
	public static final String ADDRESS = "address";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String ACCOUNT_LOCATION_ID = "accountLocationId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String LOCATION = "location";
	public static final String ACCOUNT = "account";

}

