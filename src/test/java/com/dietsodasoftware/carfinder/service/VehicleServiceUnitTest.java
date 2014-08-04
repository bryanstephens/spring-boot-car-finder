package com.dietsodasoftware.carfinder.service;

import com.dietsodasoftware.carfinder.model.Vehicle;
import com.dietsodasoftware.carfinder.model.VehicleModel;
import com.dietsodasoftware.carfinder.service.dao.VehicleDbRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class VehicleServiceUnitTest {

    @InjectMocks
    private VehicleService vehicleService = new VehicleService();

    @Mock
    private VehicleDbRepository vehicleDbRepository;

    @BeforeTest
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateVehicle() {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleService.createVehicle(vehicleModel, "2003");
    }

    @Test
    public void testFindOne() {
        vehicleService.find(1L);
    }
}