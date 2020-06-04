package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Battery;
import com.example.model.CustomBattery;
import com.example.model.Response;
import com.example.model.VendorList;
import com.example.service.BatteryService;
import com.example.service.VendorListService;

@RestController
public class BatteryRestController {
	
	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private VendorListService vendorListService;
	
	@RequestMapping(path="/battery", method=RequestMethod.GET)
	public List<Battery> getAllBatteries(){
		return batteryService.getAllBatteries();
	}
    
	@RequestMapping(value = "/battery/{id}", method = RequestMethod.GET)
	public Battery getBatteryById(@PathVariable("id") long id){
		return batteryService.getBatteryById(id);
	}
	
	@RequestMapping(path="/saveItems/{vendorId}", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Response addItems(@RequestBody List<CustomBattery> customBatteries,@PathVariable long vendorId){
		/*
		 * System.out.println("selected ids:"); for(CustomBattery c: customBatteries) {
		 * System.out.println(c.toString()); }
		 */
		
		List<VendorList> items=new ArrayList<>();
		for(CustomBattery c: customBatteries) {
			VendorList vl=new VendorList(vendorId,c.getBatteryId());
			items.add(vl);
		}
		
		List<VendorList> savedItems=vendorListService.addItems(items);
		Response response;
        if(savedItems.size()==items.size()) {
        	response = new Response("Done", "Successfully created vendor list");
        }
        else {
        	response = new Response("Error", "Vendor Not Found");
        }
        return response;
		
    }
	
	@RequestMapping(path="/showItems/{vendorId}", method=RequestMethod.GET)
	public List<VendorList> getAllItems(@PathVariable long vendorId){
		return vendorListService.getAllItems(vendorId);
	}

}
