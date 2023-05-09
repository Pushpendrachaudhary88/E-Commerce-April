package com.example.ecommerce.service;

import com.example.ecommerce.dto.RequestDto.CustomerRequestDto;
import com.example.ecommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.exception.MobileNoAlreadyPresentException;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresentException {
        // First we check customer is exist or not
        if(customerRepository.findByMobNo(customerRequestDto.getMobNo())!=null)
            throw new MobileNoAlreadyPresentException("Sorry! Customer already exists!");

        // request dto -> customer
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder() // create a cart
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();
        customer.setCart(cart); // set to the customer

        Customer savedCustomer = customerRepository.save(customer);  //  save the customer bezc customer is parent .

        // prepare response dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}