package com.seemacollection.seemacollection.mysqldao;

import com.seemacollection.seemacollection.entity.Customer;
import com.seemacollection.seemacollection.entity.CustomerCK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, CustomerCK> {
}
