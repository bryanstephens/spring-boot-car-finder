package com.dietsodasoftware.carfinder.mvc.controller;

import com.dietsodasoftware.carfinder.model.Vehicle;
import com.dietsodasoftware.carfinder.model.VehicleMake;
import com.dietsodasoftware.carfinder.model.VehicleModel;
import com.dietsodasoftware.carfinder.model.VehicleStyle;
import com.dietsodasoftware.carfinder.mvc.exception.HttpInvalidRequestError;
import com.dietsodasoftware.carfinder.mvc.view.ListResults;
import com.dietsodasoftware.carfinder.mvc.view.VehicleSubmission;
import com.dietsodasoftware.carfinder.service.VehicleMakeService;
import com.dietsodasoftware.carfinder.service.VehicleModelService;
import com.dietsodasoftware.carfinder.service.VehicleService;
import com.dietsodasoftware.carfinder.service.VehicleStyleService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: wendel.schultz
 * Date: 7/14/14
 */
@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Vehicle createVehicle(@RequestBody VehicleSubmission submission){

        if(submission.getVehicleModelId() == null){
            throw new HttpInvalidRequestError("Missing vehicle model ID");
        }

        if(submission.getYear() == null){
            throw new HttpInvalidRequestError("Missing vehicle year");
        }

        VehicleModel model = vehicleModelService.find(submission.getVehicleModelId());

        if(model == null){
            throw new HttpInvalidRequestError("Unknown vehicle model id: " + submission.getVehicleModelId());
        }

        return vehicleService.createVehicle(model, submission.getYear());

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public Vehicle load(@PathVariable("id") Long id){

        return vehicleService.find(id);

    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ListResults<Vehicle> loadAll(){
        return new ListResults<Vehicle>(vehicleService.findAll());
    }

}
