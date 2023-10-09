package org.myApplication.app;

import org.myApplication.domain.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Value("${user.nickname}")
    private String customerNickname;
    @Value("${user.password}")
    private String customerPassword;

    public CustomerDto fillInformationCustomer(CustomerDto customerDto) {
        customerDto.setNickname(customerNickname);
        customerDto.setPassword(customerPassword);
        return customerDto;
    }
}
