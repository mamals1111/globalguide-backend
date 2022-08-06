package com.myrmicatech.globalguidebackend.entity;

import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Time.class)
public abstract class Time_ extends com.myrmicatech.globalguidebackend.entity.BaseEntity_ {

	public static volatile SingularAttribute<Time, String> weekDay;
	public static volatile SingularAttribute<Time, LocalTime> closeTime;
	public static volatile SingularAttribute<Time, Location> location;
	public static volatile SingularAttribute<Time, LocalTime> openTime;
	public static volatile SingularAttribute<Time, Account> account;

	public static final String WEEK_DAY = "weekDay";
	public static final String CLOSE_TIME = "closeTime";
	public static final String LOCATION = "location";
	public static final String OPEN_TIME = "openTime";
	public static final String ACCOUNT = "account";

}

