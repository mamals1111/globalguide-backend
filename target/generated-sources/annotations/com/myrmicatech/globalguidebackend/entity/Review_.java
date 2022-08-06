package com.myrmicatech.globalguidebackend.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Review.class)
public abstract class Review_ extends com.myrmicatech.globalguidebackend.entity.BaseEntity_ {

	public static volatile SingularAttribute<Review, Boolean> isLocationVerified;
	public static volatile ListAttribute<Review, Comment> comments;
	public static volatile SingularAttribute<Review, Boolean> isPhotoVerified;
	public static volatile SingularAttribute<Review, Integer> rating;
	public static volatile SingularAttribute<Review, String> description;
	public static volatile SingularAttribute<Review, UUID> userId;
	public static volatile SingularAttribute<Review, UUID> locationId;
	public static volatile ListAttribute<Review, File> reviewFiles;
	public static volatile SingularAttribute<Review, Location> location;
	public static volatile ListAttribute<Review, Reaction> reactions;
	public static volatile SingularAttribute<Review, OwnedItem> ownedItem;
	public static volatile SingularAttribute<Review, Boolean> isReviewVerified;
	public static volatile SingularAttribute<Review, Account> account;

	public static final String IS_LOCATION_VERIFIED = "isLocationVerified";
	public static final String COMMENTS = "comments";
	public static final String IS_PHOTO_VERIFIED = "isPhotoVerified";
	public static final String RATING = "rating";
	public static final String DESCRIPTION = "description";
	public static final String USER_ID = "userId";
	public static final String LOCATION_ID = "locationId";
	public static final String REVIEW_FILES = "reviewFiles";
	public static final String LOCATION = "location";
	public static final String REACTIONS = "reactions";
	public static final String OWNED_ITEM = "ownedItem";
	public static final String IS_REVIEW_VERIFIED = "isReviewVerified";
	public static final String ACCOUNT = "account";

}

