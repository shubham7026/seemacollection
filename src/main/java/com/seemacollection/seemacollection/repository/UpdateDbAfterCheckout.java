package com.seemacollection.seemacollection.repository;

import com.seemacollection.seemacollection.dto.CustomerDTO;
import com.seemacollection.seemacollection.h2dao.ProductDao;
import com.seemacollection.seemacollection.mysqldao.UserDAO;
import com.seemacollection.seemacollection.dto.ProductDTO;
import com.seemacollection.seemacollection.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UpdateDbAfterCheckout {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CustomerRepository customerRepository;

    //@Transactional // below 2 update in different db if we add @Transactional then spring will consfuse with transaction manager it has to use
    // we can also provide txn manager name here but the issue iss both dao classes saves data in different db so we need to proivide 2 txn managers
    // which we cant do in @Transaction for now
    // to solve this issue we need a single txn manager
    public void updatePointsAndQuantity(CustomerDTO customerDTO, ProductDTO product){
        //userDAO.updatePointsById(UserRepository.idGenerator(user.getEmail()), user.getPoints());
        customerRepository.addCustomer(customerDTO);
        productDao.updateQuantityById(product.getId(), product.getStockQuantity());
    }
}
