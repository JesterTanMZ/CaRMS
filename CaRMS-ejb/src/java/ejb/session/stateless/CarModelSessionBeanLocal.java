/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CarModel;
import java.util.List;
import javax.ejb.Local;
import util.exception.CarCategoryNotFoundException;
import util.exception.CarModelNotFoundException;

/**
 *
 * @author Jester
 */
@Local
public interface CarModelSessionBeanLocal {

    public List<CarModel> retrieveAllModels();

    public CarModel retrieveModelByModelId(Long modelId) throws CarModelNotFoundException;

    public CarModel createCarModel(Long carCategoryId, CarModel newModel) throws CarCategoryNotFoundException;

}
