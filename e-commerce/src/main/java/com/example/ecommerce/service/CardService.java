package com.example.ecommerce.service;

import com.example.ecommerce.dto.RequestDto.CardRequestDto;
import com.example.ecommerce.dto.ResponseDto.CardResponseDto;
import com.example.ecommerce.exception.InvalidCustomerException;
import com.example.ecommerce.model.Card;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
       // first we check customer is exist or not.
        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if(customer==null){
            throw new InvalidCustomerException("Sorry! The customer doesn't exists");
        }
        //  request dto through  builder
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer); // set the customer to card.

        customer.getCards().add(card); // set the current card to the customer.
        customerRepository.save(customer);  // save the customer

        // response dto
        return CardResponseDto.builder()
                .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();

    }
}