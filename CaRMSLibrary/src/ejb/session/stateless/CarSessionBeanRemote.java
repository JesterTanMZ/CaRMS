/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Car;
import java.util.List;
import javax.ejb.Remote;
import util.exception.CarExistException;
import util.exception.CarModelNotFoundException;
import util.exception.CarNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.OutletNotFoundException;

/**
 *
 * @author Jester
 */
@Remote
public interface CarSessionBeanRemote {
    
    public Long createNewCar(Long modelId, Long outletId, Car newCar) throws CarExistException, OutletNotFoundException, CarModelNotFoundException, InputDataValidationException;
    
    public List<Car> retrieveAllCars();
    
    public void deleteCar(Long carId) throws CarNotFoundException;
    
    public void updateCar(Car car) throws CarNotFoundException, InputDataValidationException;
    
    public Car retrieveCarById(Long carId) throws CarNotFoundException;
    
}
