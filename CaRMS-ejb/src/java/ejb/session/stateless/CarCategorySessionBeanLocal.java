/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CarCategory;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jester
 */
@Local
public interface CarCategorySessionBeanLocal {

    public Long createCarCategory(CarCategory newCarCategory);

    public List<CarCategory> retrieveAllCarCategories();

    public CarCategory retrieveCarCategoryByCarCategoryId(Long carCategoryId);
    
}
