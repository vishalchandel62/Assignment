package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.exception.LeadAlreadyExistsException;
import com.demo.model.Lead;
import com.demo.repository.LeadRepository;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    public Lead createLead(Lead lead) {
        if (leadRepository.existsByLeadId(lead.getLeadId())) {
            throw new LeadAlreadyExistsException("Lead Already Exists in the database with the lead id");
        }

        if (leadRepository.existsByEmail(lead.getEmail())) {
            throw new LeadAlreadyExistsException("Lead Already Exists in the database with the email");
        }

        // Additional validation logic can be added here

        return leadRepository.save(lead);
    }

    public List<Lead> getLeadsByMobileNumber(String mobileNumber) {
        return leadRepository.findByMobileNumber(mobileNumber);
    }
}
