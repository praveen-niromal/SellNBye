package com.example.ecom.service;

import com.example.ecom.model.Shipment;
import com.example.ecom.repository.ShippingRepository;
import com.example.ecom.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    public Shipment shipProduct(String address) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	Long current_user_id = ((UserPrincipal)auth.getPrincipal()).getId();

        Shipment shipment = new Shipment(current_user_id,address,"PENDING");

        return shippingRepository.save(shipment);

    }

    public List<Shipment> getAllShipments() {
        return shippingRepository.findAll();
    }

    public Optional<Shipment> findShipment(Long shipmentID) {
        return shippingRepository.findById(shipmentID);
    }

    public void deleteShipment(Shipment shipment) {
        shippingRepository.delete(shipment);
    }

    public Shipment updateShipment(Shipment update_shipment) {
        return shippingRepository.save(update_shipment);
    }
}
