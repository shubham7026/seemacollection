package com.seemacollection.seemacollection.repository;

import com.seemacollection.seemacollection.dto.CustomerDTO;
import com.seemacollection.seemacollection.entity.Customer;
import com.seemacollection.seemacollection.entity.CustomerCK;
import com.seemacollection.seemacollection.entity.CustomerProductDetails;
import com.seemacollection.seemacollection.mapper.EntityToDTOMapper;
import com.seemacollection.seemacollection.mysqldao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private EntityToDTOMapper entityToDTOMapper;

    public CustomerDTO getCustomer(String email, String phoneNUmber){

        CustomerCK key = new CustomerCK();
        key.setPhone_number(phoneNUmber);
        key.setEmail(email);
        try{
            Customer customer = customerDao.getReferenceById(key);
            return CustomerDTO.builder()
                    .name(customer.getName())
                    .email(key.getEmail())
                    .phone_number(key.getPhone_number())
                    .productId(customer.getCustomerProductDetails()!=null ? customer.getCustomerProductDetails().getProductId() : null)
                    .build();
        }catch (jakarta.persistence.EntityNotFoundException exception){
            return null;
        }
    }

    public CustomerDTO addCustomer(CustomerDTO customer) {
        Customer ent = getEntity(customer);
        customerDao.save(ent);
        return customer;
    }

    private Customer getEntity(CustomerDTO req) {
        Customer resp = new Customer();
        resp.setCustomerCK(new CustomerCK(req.getEmail(), req.getPhone_number()));
        resp.setName(req.getName());

        if (!req.isNewCustomer()){
            CustomerProductDetails customerProductDetails = CustomerProductDetails.builder()
                    .productId(req.getProductId())
                    .productPrice(req.getProductPrice())
                    .quantity(req.getQuantity())
                    .totalAmount(req.getTotalAmount())
                    .build();
            resp.setCustomerProductDetails(customerProductDetails);
        }

        return resp;
    }

    public void addAddress(CustomerDTO cus) {

    }
}
