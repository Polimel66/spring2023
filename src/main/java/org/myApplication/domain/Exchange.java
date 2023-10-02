package org.myApplication.domain;

import org.myApplication.app.CustomerService;
import org.myApplication.extern.api.ExchangeController;

public class Exchange {
    public void printLayer()
    {
        System.out.println("This is domain layer.");
    }

//    public void connectionToAppLayer() // Нарушает то, что domain не может обращаться к app. Тест падает.
//    {
//        CustomerService customerService = new CustomerService();
//        customerService.printLayer();
//    }
}
