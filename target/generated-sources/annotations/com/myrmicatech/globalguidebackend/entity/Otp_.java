package com.myrmicatech.globalguidebackend.entity;

import com.myrmicatech.globalguidebackend.enums.OtpStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Otp.class)
public abstract class Otp_ {

	public static volatile SingularAttribute<Otp, LocalDateTime> createdDate;
	public static volatile SingularAttribute<Otp, Boolean> isDeleted;
	public static volatile SingularAttribute<Otp, LocalDateTime> deletedDate;
	public static volatile SingularAttribute<Otp, LocalDateTime> expiryTime;
	public static volatile SingularAttribute<Otp, String> otp;
	public static volatile SingularAttribute<Otp, OtpStatus> otpStatus;
	public static volatile SingularAttribute<Otp, Boolean> isActive;
	public static volatile SingularAttribute<Otp, UUID> userId;
	public static volatile SingularAttribute<Otp, Account> account;
	public static volatile SingularAttribute<Otp, LocalDateTime> updatedAt;

	public static final String CREATED_DATE = "createdDate";
	public static final String IS_DELETED = "isDeleted";
	public static final String DELETED_DATE = "deletedDate";
	public static final String EXPIRY_TIME = "expiryTime";
	public static final String OTP = "otp";
	public static final String OTP_STATUS = "otpStatus";
	public static final String IS_ACTIVE = "isActive";
	public static final String USER_ID = "userId";
	public static final String ACCOUNT = "account";
	public static final String UPDATED_AT = "updatedAt";

}

