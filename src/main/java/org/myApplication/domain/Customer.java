package org.myApplication.domain;

import org.myApplication.extern.api.CustomerController;

public class Customer {
    public void printLayer()
    {
        System.out.println("This is domain layer.");
    }

//    public void connectionToExternLayer() // Нарушает то, что domain не может обращаться к extern. Тест падает.
//    {
//        CustomerController customerController = new CustomerController();
//        customerController.printLayer();
//    }
}
