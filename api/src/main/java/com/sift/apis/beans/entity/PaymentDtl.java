package com.sift.apis.beans.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the payment_dtls database table.
 * 
 */
@Entity
@Table(name="payment_dtls")
@NamedQuery(name="PaymentDtl.findAll", query="SELECT p FROM PaymentDtl p")
public class PaymentDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PAYMENT_DTLS_ID")
	private String paymentDtlsId;

	@Column(name="BANK_REF_NUM")
	private String bankRefNum;

	@Column(name="CREATED_TS")
	private String createdTs;

	@Column(name="MODIFIED_TS")
	private String modifiedTs;

	@Column(name="PAYMENT_AMOUNT")
	private float paymentAmount;

	@Column(name="PAYMENT_GATEWAY")
	private String paymentGateway;

	@Column(name="PAYMENT_MODE")
	private String paymentMode;

	@Column(name="PAYMENT_STATUS")
	private String paymentStatus;

	@Column(name="PAYMENT_TS")
	private String paymentTs;

	@Column(name="TXN_DATA")
	private String txnData;

	@Column(name="TXN_REF_NO")
	private String txnRefNo;
/*
	//bi-directional many-to-one association to ClubDealsSubDtl
	@OneToMany(mappedBy="paymentDtl")
	private List<ClubDealsSubDtl> clubDealsSubDtls;

	//bi-directional many-to-one association to UserClubSubDtl
	@OneToMany(mappedBy="paymentDtl")
	private List<UserClubSubDtl> userClubSubDtls;

	//bi-directional many-to-one association to UserMasterDtl
	@OneToMany(mappedBy="paymentDtl")
	private List<UserMasterDtl> userMasterDtls;
*/
	public PaymentDtl() {
	}

	public String getPaymentDtlsId() {
		return this.paymentDtlsId;
	}

	public void setPaymentDtlsId(String paymentDtlsId) {
		this.paymentDtlsId = paymentDtlsId;
	}

	public String getBankRefNum() {
		return this.bankRefNum;
	}

	public void setBankRefNum(String bankRefNum) {
		this.bankRefNum = bankRefNum;
	}

	public String getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}

	public String getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(String modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public float getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentGateway() {
		return this.paymentGateway;
	}

	public void setPaymentGateway(String paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentTs() {
		return this.paymentTs;
	}

	public void setPaymentTs(String paymentTs) {
		this.paymentTs = paymentTs;
	}

	public String getTxnData() {
		return this.txnData;
	}

	public void setTxnData(String txnData) {
		this.txnData = txnData;
	}

	public String getTxnRefNo() {
		return this.txnRefNo;
	}

	public void setTxnRefNo(String txnRefNo) {
		this.txnRefNo = txnRefNo;
	}

/*	public List<ClubDealsSubDtl> getClubDealsSubDtls() {
		return this.clubDealsSubDtls;
	}

	public void setClubDealsSubDtls(List<ClubDealsSubDtl> clubDealsSubDtls) {
		this.clubDealsSubDtls = clubDealsSubDtls;
	}

	public ClubDealsSubDtl addClubDealsSubDtl(ClubDealsSubDtl clubDealsSubDtl) {
		getClubDealsSubDtls().add(clubDealsSubDtl);
		clubDealsSubDtl.setPaymentDtl(this);

		return clubDealsSubDtl;
	}

	public ClubDealsSubDtl removeClubDealsSubDtl(ClubDealsSubDtl clubDealsSubDtl) {
		getClubDealsSubDtls().remove(clubDealsSubDtl);
		clubDealsSubDtl.setPaymentDtl(null);

		return clubDealsSubDtl;
	}

	public List<UserClubSubDtl> getUserClubSubDtls() {
		return this.userClubSubDtls;
	}

	public void setUserClubSubDtls(List<UserClubSubDtl> userClubSubDtls) {
		this.userClubSubDtls = userClubSubDtls;
	}

	public UserClubSubDtl addUserClubSubDtl(UserClubSubDtl userClubSubDtl) {
		getUserClubSubDtls().add(userClubSubDtl);
		userClubSubDtl.setPaymentDtl(this);

		return userClubSubDtl;
	}

	public UserClubSubDtl removeUserClubSubDtl(UserClubSubDtl userClubSubDtl) {
		getUserClubSubDtls().remove(userClubSubDtl);
		userClubSubDtl.setPaymentDtl(null);

		return userClubSubDtl;
	}

	public List<UserMasterDtl> getUserMasterDtls() {
		return this.userMasterDtls;
	}

	public void setUserMasterDtls(List<UserMasterDtl> userMasterDtls) {
		this.userMasterDtls = userMasterDtls;
	}

	public UserMasterDtl addUserMasterDtl(UserMasterDtl userMasterDtl) {
		getUserMasterDtls().add(userMasterDtl);
		userMasterDtl.setPaymentDtl(this);

		return userMasterDtl;
	}

	public UserMasterDtl removeUserMasterDtl(UserMasterDtl userMasterDtl) {
		getUserMasterDtls().remove(userMasterDtl);
		userMasterDtl.setPaymentDtl(null);

		return userMasterDtl;
	}
*/
}