package org.myApplication.extern.api;

import lombok.AllArgsConstructor;
import org.myApplication.app.CustomerService;
import org.myApplication.domain.CustomerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/customer")
    public String getCustomer() {
        CustomerDto customer = customerService.fillInformationCustomer(new CustomerDto());
        return "Данные покупателя: никнейм - " + customer.getNickname() + ", пароль - " + customer.getPassword();
    }
}
