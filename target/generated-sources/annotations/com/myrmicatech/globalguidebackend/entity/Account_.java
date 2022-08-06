package com.myrmicatech.globalguidebackend.entity;

import com.myrmicatech.globalguidebackend.enums.RoleType;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ extends com.myrmicatech.globalguidebackend.entity.BaseEntity_ {

	public static volatile SingularAttribute<Account, RoleType> role;
	public static volatile ListAttribute<Account, Comment> comments;
	public static volatile SingularAttribute<Account, File> avatarFile;
	public static volatile ListAttribute<Account, OwnedItem> ownedItems;
	public static volatile ListAttribute<Account, LocationLog> locationLogs;
	public static volatile SingularAttribute<Account, String> fullName;
	public static volatile SingularAttribute<Account, Otp> otp;
	public static volatile SingularAttribute<Account, UUID> avatar;
	public static volatile SingularAttribute<Account, String> passwordHash;
	public static volatile SingularAttribute<Account, Double> token;
	public static volatile SingularAttribute<Account, Double> totalFollowings;
	public static volatile ListAttribute<Account, Time> times;
	public static volatile ListAttribute<Account, Account> followers;
	public static volatile ListAttribute<Account, Review> reviews;
	public static volatile ListAttribute<Account, Account> followings;
	public static volatile ListAttribute<Account, File> files;
	public static volatile ListAttribute<Account, Reaction> reactions;
	public static volatile SingularAttribute<Account, Double> totalFollowers;
	public static volatile SingularAttribute<Account, String> email;
	public static volatile SingularAttribute<Account, String> username;

	public static final String ROLE = "role";
	public static final String COMMENTS = "comments";
	public static final String AVATAR_FILE = "avatarFile";
	public static final String OWNED_ITEMS = "ownedItems";
	public static final String LOCATION_LOGS = "locationLogs";
	public static final String FULL_NAME = "fullName";
	public static final String OTP = "otp";
	public static final String AVATAR = "avatar";
	public static final String PASSWORD_HASH = "passwordHash";
	public static final String TOKEN = "token";
	public static final String TOTAL_FOLLOWINGS = "totalFollowings";
	public static final String TIMES = "times";
	public static final String FOLLOWERS = "followers";
	public static final String REVIEWS = "reviews";
	public static final String FOLLOWINGS = "followings";
	public static final String FILES = "files";
	public static final String REACTIONS = "reactions";
	public static final String TOTAL_FOLLOWERS = "totalFollowers";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

