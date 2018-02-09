package com.shoval.coupons.system.tables;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepo extends CrudRepository<Company, Long>{
	
	Company findCompanyByName(String name);
	boolean existsByName(String name);
}
