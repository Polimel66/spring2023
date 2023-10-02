package org.myApplication.app;

import org.myApplication.extern.api.CustomerController;

public class CustomerService {
    public void printLayer()
    {
        System.out.println("This is app layer.");
    }

//    public void connectionToExternLayer() // Нарушает то, что app не может обращаться к extern. Тест падает.
//    {
//        CustomerController customerController = new CustomerController();
//        customerController.printLayer();
//    }
}
