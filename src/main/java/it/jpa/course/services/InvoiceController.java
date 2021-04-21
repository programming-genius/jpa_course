package it.jpa.course.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.jpa.course.beans.InvoiceRequest;
import it.jpa.course.entities.OrderV3;
import it.jpa.course.exceptions.RollbackException;
import it.jpa.course.repositories.InvoiceRepository;

@RestController
public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepository;

	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

	@PostMapping(value="/invoice/v3/add", consumes = "application/json", produces = "text/plain")
	public String add(@RequestBody InvoiceRequest invoiceRequest) {
		logger.info("[InvoiceController::add] Start");
		try {
			invoiceRepository.add(invoiceRequest.getInvoice(), invoiceRequest.getOrder().getId());
			logger.info("[InvoiceController::add] OK");
		} catch (RollbackException e) {
			logger.info("[InvoiceController::add] KO");
			return "Errore nella fattura";
		}
		return "Fattura inserita";
	}

	@PutMapping(value="/invoice/v3/upd", consumes = "application/json", produces = "text/plain")
	public String upd(@RequestBody InvoiceRequest invoiceRequest) {
		logger.info("[InvoiceController::upd] Start");
		try {
			OrderV3 order = new OrderV3();
			order.setId(invoiceRequest.getOrder().getId());
			invoiceRequest.getInvoice().setOrderV3(order);
			invoiceRepository.upd(invoiceRequest.getInvoice());
			logger.info("[InvoiceController::upd] OK");
		} catch (RollbackException e) {
			logger.info("[InvoiceController::upd] KO");
			return "Errore nell'aggiornamento della fattura";
		} catch (RuntimeException e) {
			logger.info("[InvoiceController::upd] KO");
			return "Errore nell'aggiornamento della fattura";
		}
		return "Fattura aggiornata";
	}

	@DeleteMapping(value="/invoice/v3/del", consumes = "application/json", produces = "text/plain")
	public String del(@RequestBody InvoiceRequest invoiceRequest) {
		logger.info("[InvoiceController::del] Start");
		try {
			invoiceRepository.del(invoiceRequest.getInvoice());
			logger.info("[InvoiceController::del] OK");
		} catch (RollbackException e) {
			logger.info("[InvoiceController::del] KO");
			return "Errore cancellazione fattura";
		}
		return "Fattura cancellata";
	}
}
