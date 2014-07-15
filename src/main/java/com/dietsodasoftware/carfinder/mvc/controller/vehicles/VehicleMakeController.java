package com.dietsodasoftware.carfinder.mvc.controller.vehicles;

import com.dietsodasoftware.carfinder.model.VehicleMake;
import com.dietsodasoftware.carfinder.mvc.view.ListResults;
import com.dietsodasoftware.carfinder.service.VehicleMakeService;
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
@RequestMapping("/vehicles/makes")
public class VehicleMakeController {

    @Autowired
    private VehicleMakeService makeService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public VehicleMake createMake(@RequestBody VehicleMake make){
        return makeService.createVehicleMake(make.getName());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public VehicleMake load(@PathVariable("id") Long id){
        return makeService.find(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ListResults<VehicleMake> list(){
        return new ListResults<VehicleMake>(makeService.findAll());
    }

}
