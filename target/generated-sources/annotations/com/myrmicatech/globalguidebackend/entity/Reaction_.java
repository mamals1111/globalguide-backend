package com.myrmicatech.globalguidebackend.entity;

import com.myrmicatech.globalguidebackend.entity.ids.AccountReviewId;
import com.myrmicatech.globalguidebackend.enums.ReactionType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reaction.class)
public abstract class Reaction_ {

	public static volatile SingularAttribute<Reaction, LocalDateTime> createdDate;
	public static volatile SingularAttribute<Reaction, ReactionType> reactionType;
	public static volatile SingularAttribute<Reaction, Boolean> isDeleted;
	public static volatile SingularAttribute<Reaction, LocalDateTime> deletedDate;
	public static volatile SingularAttribute<Reaction, Review> review;
	public static volatile SingularAttribute<Reaction, AccountReviewId> accountReviewId;
	public static volatile SingularAttribute<Reaction, Boolean> isActive;
	public static volatile SingularAttribute<Reaction, Account> account;
	public static volatile SingularAttribute<Reaction, LocalDateTime> updatedAt;

	public static final String CREATED_DATE = "createdDate";
	public static final String REACTION_TYPE = "reactionType";
	public static final String IS_DELETED = "isDeleted";
	public static final String DELETED_DATE = "deletedDate";
	public static final String REVIEW = "review";
	public static final String ACCOUNT_REVIEW_ID = "accountReviewId";
	public static final String IS_ACTIVE = "isActive";
	public static final String ACCOUNT = "account";
	public static final String UPDATED_AT = "updatedAt";

}

