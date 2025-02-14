package com.accenture.lkm.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.entity.ComplaintEntity;


@RepositoryDefinition(idClass = Integer.class,domainClass = ComplaintEntity.class)
@Transactional(value = "txManager")
public interface ComplaintDao {
	public ComplaintEntity save(ComplaintEntity complaintEntity);
	
	/**
    This method is retrieving Complaint details where the customerName and the complaintTypeId is matching to the given values
 	*/
	@Query(name="getCustomerByComplaintType")
	public ComplaintEntity getCustomerByComplaintType(String customerName,int complaintTypeId);
	
	@Query(name="getComplaintDetailsByDate")
	List<ComplaintEntity> getComplaintDetailsByDate(Date fromDate,Date toDate);
}

