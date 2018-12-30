package com.kartoffelkopf.waterBills.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kartoffelkopf.waterBills.model.Bill;
import com.kartoffelkopf.waterBills.service.BillService;

@Controller
public class BillController {

	@Autowired
	private BillService billService;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("bills", billService.findAll());
		return "index";
	}
	
	@RequestMapping("/bills")
    public String billIndex(Model model) {
        model.addAttribute("bills", billService.findAll());
        return "index";
    }

    @RequestMapping("/bills/add")
    public String billAddForm(Model model) {
        model.addAttribute("bill",new Bill());
        model.addAttribute("action", "/bills");
        model.addAttribute("heading", "Add");
        model.addAttribute("submit", "Add");
        return "form";
    }

    @RequestMapping(value = "/bills", method = RequestMethod.POST)
    public String billAddPost(Bill bill) {
    	billService.save(bill);
        return "redirect:/bills";
    }

    @RequestMapping("/bills/{billId}/edit")
    public String billEditForm(@PathVariable Long billId, Model model) {    	
        model.addAttribute("bill", billService.findById(billId));
        model.addAttribute("action", String.format("/bills/%s", billId));
        model.addAttribute("heading", "Edit");
        model.addAttribute("submit", "Save");
        return "form";
    }

    @RequestMapping(value = "/bills/{billId}", method = RequestMethod.POST)
    public String billEditPost(Bill bill) {
        billService.save(bill);
        if (bill.isReadingAdded()) {
        	billService.addReading(bill.getId(), bill.getReadingDate(), bill.getReadingUnits(), bill.getReadingFilePath());
        }
        return "redirect:/bills";
    }

    @RequestMapping(value = "/bills/{billId}/delete", method = RequestMethod.POST)
    public String billDelete(@PathVariable Long billId) {
        Bill bill = billService.findById(billId);
        billService.delete(bill);
        return "redirect:/bills";
    }
    
    @RequestMapping("/bills/{billId}/add-reading")
    public String billAddReadingForm(@PathVariable Long billId, Model model) {
    	model.addAttribute("bill", billService.findById(billId));
    	model.addAttribute("action", String.format("/bills/%s/add-reading", billId));
    	model.addAttribute("heading", "Add");
    	model.addAttribute("submit", "Add");
    	return "reading-form";
    }
    
    @RequestMapping(value = "/bills/{billId}/add-reading", method = RequestMethod.POST)
    public String billAddReadingPost(Bill bill) {
    	billService.addReading(bill.getId(), bill.getReadingDate(), bill.getReadingUnits(), bill.getReadingFilePath());
    	billService.calculate(bill);
    	return "redirect:/bills";
    }
}
