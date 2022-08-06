package com.myrmicatech.globalguidebackend.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ extends com.myrmicatech.globalguidebackend.entity.BaseEntity_ {

	public static volatile SingularAttribute<Comment, Review> review;
	public static volatile SingularAttribute<Comment, String> description;
	public static volatile SingularAttribute<Comment, UUID> userId;
	public static volatile SingularAttribute<Comment, UUID> reviewId;
	public static volatile SingularAttribute<Comment, Account> account;

	public static final String REVIEW = "review";
	public static final String DESCRIPTION = "description";
	public static final String USER_ID = "userId";
	public static final String REVIEW_ID = "reviewId";
	public static final String ACCOUNT = "account";

}

