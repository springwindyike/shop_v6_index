
package com.igomall.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.BusinessCashDao;
import com.igomall.entity.Business;
import com.igomall.entity.BusinessCash;
import com.igomall.entity.BusinessCash.Status;

/**
 * Dao - 商家提现
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Repository
public class BusinessCashDaoImpl extends BaseDaoImpl<BusinessCash, Long> implements BusinessCashDao {

	@Override
	public Page<BusinessCash> findPage(Status status, String bank, String account, Business business, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BusinessCash> criteriaQuery = criteriaBuilder.createQuery(BusinessCash.class);
		Root<BusinessCash> root = criteriaQuery.from(BusinessCash.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (status != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("status"), status));
		}
		if (bank != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("bank"), bank));
		}
		if (account != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("account"), account));
		}
		if (business != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("business"), business));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

	@Override
	public Long count(Status status, String bank, String account, Business business) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BusinessCash> criteriaQuery = criteriaBuilder.createQuery(BusinessCash.class);
		Root<BusinessCash> root = criteriaQuery.from(BusinessCash.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (status != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("status"), status));
		}
		if (bank != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("bank"), bank));
		}
		if (account != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("account"), account));
		}
		if (business != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("business"), business));
		}
		criteriaQuery.where(restrictions);
		return super.count(criteriaQuery, null);
	}

}