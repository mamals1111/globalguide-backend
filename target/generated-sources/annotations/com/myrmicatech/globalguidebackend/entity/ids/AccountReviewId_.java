package com.myrmicatech.globalguidebackend.entity.ids;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountReviewId.class)
public abstract class AccountReviewId_ {

	public static volatile SingularAttribute<AccountReviewId, UUID> userId;
	public static volatile SingularAttribute<AccountReviewId, UUID> reviewId;

	public static final String USER_ID = "userId";
	public static final String REVIEW_ID = "reviewId";

}

