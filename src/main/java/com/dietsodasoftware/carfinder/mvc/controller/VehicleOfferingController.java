package com.dietsodasoftware.carfinder.mvc.controller;

import com.dietsodasoftware.carfinder.model.Vehicle;
import com.dietsodasoftware.carfinder.model.VehicleOffering;
import com.dietsodasoftware.carfinder.mvc.exception.HttpInvalidRequestError;
import com.dietsodasoftware.carfinder.mvc.exception.HttpNotFoundError;
import com.dietsodasoftware.carfinder.mvc.view.ListResults;
import com.dietsodasoftware.carfinder.mvc.view.VehicleOfferingSubmission;
import com.dietsodasoftware.carfinder.service.VehicleOfferingService;
import com.dietsodasoftware.carfinder.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: wendel.schultz
 * Date: 7/14/14
 */
@Controller
@RequestMapping("/offerings")
public class VehicleOfferingController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleOfferingService vehicleOfferingService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public VehicleOffering createOffering(@RequestBody VehicleOfferingSubmission offering){
        if(offering.getVehicleId() == null){
            throw new HttpInvalidRequestError("Must provide vehicle ID");
        }

        Vehicle vehicle = vehicleService.find(offering.getVehicleId());

        if(vehicle == null){
            throw new HttpInvalidRequestError("Unknown vehicle ID: " + offering.getVehicleId());
        }

        final VehicleOffering newOffering = vehicleOfferingService.createOffering(vehicle, offering.getTitle(), offering.getDescription(), offering.getPrice());

        return newOffering;

    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public VehicleOffering loadOffering(@PathVariable("id") Long id){

        final VehicleOffering offering = vehicleOfferingService.find(id);

        if(offering == null){
            throw new HttpNotFoundError("Unknown ID: " + id);
        }

        return offering;
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ListResults<VehicleOffering> list(){

        List<VehicleOffering> offerings = vehicleOfferingService.findAll();

        return new ListResults<VehicleOffering>(offerings);

    }
}
