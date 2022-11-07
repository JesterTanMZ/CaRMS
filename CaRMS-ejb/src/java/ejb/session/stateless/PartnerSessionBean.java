/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Partner;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.InputDataValidationException;
import util.exception.PartnerNameExistException;
import util.exception.PartnerNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author michelsim
 */
@Stateless
public class PartnerSessionBean implements PartnerSessionBeanRemote, PartnerSessionBeanLocal {

    @PersistenceContext(unitName = "CaRMS-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;


    public PartnerSessionBean(ValidatorFactory validatorFactory, Validator validator) {
        this.validatorFactory = validatorFactory;
        this.validator = validator;
    }
    
    @Override
    public Long createPartner(Partner newPartner) throws PartnerNameExistException, UnknownPersistenceException, InputDataValidationException {

        Set<ConstraintViolation<Partner>> constraintViolations = validator.validate(newPartner);

        if (constraintViolations.isEmpty()) {
            try {
                em.persist(newPartner);
                em.flush();
                return newPartner.getPartnerId();
            } 
            catch (PersistenceException ex) {
                if(ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException")) {
                    
                    if(ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) {
                        throw new PartnerNameExistException("Partner name already exists!");
                    } 
                    else {
                        throw new UnknownPersistenceException(ex.getMessage());
                    }
                } 
                else {
                    throw new UnknownPersistenceException(ex.getMessage());
                }
            }
        }
        else {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public Partner retrievePartnerById(Long partnerId) throws PartnerNotFoundException {
         Partner partner = em.find(Partner.class, partnerId);
        
        if (partner!= null) {
            return partner;
        } 
        else {
            throw new PartnerNotFoundException("Partner with partner id: " + partnerId + " not found!");
        }
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Partner>>constraintViolations) {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        return msg;
    }  
}
