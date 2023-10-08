package org.myApplication.extern.api;

import org.myApplication.domain.Customer;

public class CustomerController {
    public void printLayer() {
        System.out.println("This is extern layer.");
    }

    public void connectionToDomainLayer() // Не нарушает архитектуру. Тест проходит.
    {
        Customer customer = new Customer();
        customer.printLayer();
    }
}
