package com.myrmicatech.globalguidebackend.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(File.class)
public abstract class File_ extends com.myrmicatech.globalguidebackend.entity.BaseEntity_ {

	public static volatile SingularAttribute<File, String> fileName;
	public static volatile SingularAttribute<File, UUID> locationBannerId;
	public static volatile SingularAttribute<File, Item> itemIcon;
	public static volatile SingularAttribute<File, UUID> userId;
	public static volatile SingularAttribute<File, Location> locationBanner;
	public static volatile SingularAttribute<File, String> fileDownloadUri;
	public static volatile SingularAttribute<File, Account> accountAvatar;
	public static volatile SingularAttribute<File, Long> size;
	public static volatile SingularAttribute<File, Location> locationAvatar;
	public static volatile SingularAttribute<File, Review> review;
	public static volatile SingularAttribute<File, Item> itemPhoto;
	public static volatile SingularAttribute<File, String> checkSum;
	public static volatile SingularAttribute<File, UUID> reviewId;
	public static volatile SingularAttribute<File, String> fileType;
	public static volatile SingularAttribute<File, Account> account;

	public static final String FILE_NAME = "fileName";
	public static final String LOCATION_BANNER_ID = "locationBannerId";
	public static final String ITEM_ICON = "itemIcon";
	public static final String USER_ID = "userId";
	public static final String LOCATION_BANNER = "locationBanner";
	public static final String FILE_DOWNLOAD_URI = "fileDownloadUri";
	public static final String ACCOUNT_AVATAR = "accountAvatar";
	public static final String SIZE = "size";
	public static final String LOCATION_AVATAR = "locationAvatar";
	public static final String REVIEW = "review";
	public static final String ITEM_PHOTO = "itemPhoto";
	public static final String CHECK_SUM = "checkSum";
	public static final String REVIEW_ID = "reviewId";
	public static final String FILE_TYPE = "fileType";
	public static final String ACCOUNT = "account";

}

