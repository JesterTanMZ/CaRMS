/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Customer;
import javax.ejb.Local;
import util.exception.CustomerNameExistException;
import util.exception.CustomerNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author michelsim
 */
@Local
public interface CustomerSessionBeanLocal {

    public Long createCustomer(Customer newCustomer) throws InputDataValidationException, UnknownPersistenceException, CustomerNameExistException;

    public Customer retrieveCustomerById(Long customerId) throws CustomerNotFoundException;
    
}
