package com.myrmicatech.globalguidebackend.entity;

import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Location.class)
public abstract class Location_ extends com.myrmicatech.globalguidebackend.entity.BaseEntity_ {

	public static volatile SingularAttribute<Location, String> website;
	public static volatile SingularAttribute<Location, String> address;
	public static volatile SingularAttribute<Location, File> avatarFile;
	public static volatile ListAttribute<Location, LocationLog> locationLogs;
	public static volatile SingularAttribute<Location, BigDecimal> latitude;
	public static volatile SingularAttribute<Location, String> description;
	public static volatile SingularAttribute<Location, UUID> avatar;
	public static volatile SingularAttribute<Location, UUID> userId;
	public static volatile SingularAttribute<Location, Long> totalReviews;
	public static volatile ListAttribute<Location, Time> times;
	public static volatile SingularAttribute<Location, String> phoneNumber;
	public static volatile ListAttribute<Location, Review> reviews;
	public static volatile SingularAttribute<Location, String> name;
	public static volatile ListAttribute<Location, File> bannerFiles;
	public static volatile SingularAttribute<Location, Double> avgRating;
	public static volatile ListAttribute<Location, Category> categories;
	public static volatile SingularAttribute<Location, BigDecimal> longitude;

	public static final String WEBSITE = "website";
	public static final String ADDRESS = "address";
	public static final String AVATAR_FILE = "avatarFile";
	public static final String LOCATION_LOGS = "locationLogs";
	public static final String LATITUDE = "latitude";
	public static final String DESCRIPTION = "description";
	public static final String AVATAR = "avatar";
	public static final String USER_ID = "userId";
	public static final String TOTAL_REVIEWS = "totalReviews";
	public static final String TIMES = "times";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String REVIEWS = "reviews";
	public static final String NAME = "name";
	public static final String BANNER_FILES = "bannerFiles";
	public static final String AVG_RATING = "avgRating";
	public static final String CATEGORIES = "categories";
	public static final String LONGITUDE = "longitude";

}

