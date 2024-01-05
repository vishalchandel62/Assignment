package com.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.LeadAlreadyExistsException;
import com.demo.model.ErrorResponse;
import com.demo.model.Lead;
import com.demo.model.LeadResponse;
import com.demo.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

	@Autowired
	private LeadService leadService;

	@PostMapping
	public ResponseEntity<?> createLead(@Valid @RequestBody Lead lead) {
		try {
			Lead createdLead = leadService.createLead(lead);
			return ResponseEntity.status(HttpStatus.CREATED).body("Created Leads Successfully");
		} catch (LeadAlreadyExistsException e) {
			ErrorResponse errorResponse = new ErrorResponse("E10010", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		}
	}

	@GetMapping("/byMobile/{mobileNumber}")
	public ResponseEntity<?> getLeadsByMobileNumber(@PathVariable String mobileNumber) {
		try {
			List<Lead> leads = leadService.getLeadsByMobileNumber(mobileNumber);

			if (leads.isEmpty()) {
				ErrorResponse errorResponse = new ErrorResponse("E10011",
						"No Lead found with the Mobile Number " + mobileNumber);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
			}

			return ResponseEntity.status(HttpStatus.OK).body(new LeadResponse("success", leads));
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse("E10000", "Internal Server Error");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}