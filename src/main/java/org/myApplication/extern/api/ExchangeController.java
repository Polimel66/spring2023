package org.myApplication.extern.api;

import org.myApplication.app.CustomerService;

public class ExchangeController {
    public void printLayer()
    {
        System.out.println("This is extern layer.");
    }

    public void connectionToAppLayer() // Не нарушает архитектуру. Тест проходит.
    {
        CustomerService customerService = new CustomerService();
        customerService.printLayer();
    }
}
