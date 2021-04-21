package it.jpa.course.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.jpa.course.beans.Registrations;
import it.jpa.course.entities.Registration;
import it.jpa.course.exceptions.RollbackException;
import it.jpa.course.repositories.RegistrationRepository;

@RestController
public class RegistrationController {

	@Autowired
	private RegistrationRepository registrationRepository;

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@PostMapping(value="/registration/add", consumes = "application/json", produces = "text/plain")
	public String add(@RequestBody Registration registration) {
		logger.info("[RegistrationController::add] Start");
		try {
			registrationRepository.add(registration);
			logger.info("[RegistrationController::add] OK");
		} catch (RollbackException e) {
			logger.info("[RegistrationController::add] KO");
			return "Errore nella registrazione";
		}
		return "Registrazione inserita";
	}

	@PutMapping(value="/registration/upd", consumes = "application/json", produces = "text/plain")
	public String upd(@RequestBody Registration registration) {
		logger.info("[RegistrationController::upd] Start");
		try {
			registrationRepository.upd(registration);
			logger.info("[RegistrationController::upd] OK");
		} catch (RollbackException e) {
			logger.info("[RegistrationController::upd] KO");
			return "Errore nell'aggiornamento della registrazione";
		} catch (RuntimeException e) {
			logger.info("[RegistrationController::upd] KO");
			return "Errore nell'aggiornamento della registrazione";
		}
		return "Registrazione aggiornata";
	}

	@DeleteMapping(value="/registration/del", consumes = "application/json", produces = "text/plain")
	public String del(@RequestBody Registration registration) {
		logger.info("[RegistrationController::del] Start");
		try {
			registrationRepository.del(registration);
			logger.info("[RegistrationController::del] OK");
		} catch (RollbackException e) {
			logger.info("[RegistrationController::del] KO");
			return "Errore cancellazione registrazione";
		}
		return "Registrazione cancellata";
	}

	@PostMapping(value="/registration/addAll", consumes = "application/json", produces = "text/plain")
	public String all(@RequestBody Registrations registrations) {
		logger.info("[RegistrationController::addAll] Start");
		try {
			registrationRepository.addAll(registrations.getRegistrations());
			logger.info("[RegistrationController::addAll] OK");
		} catch (RollbackException e) {
			logger.info("[RegistrationController::addAll] KO");
			return "Errore nell'aggiunta delle registrazioni";
		}
		return "Registrazioni aggiunte con successo";
	}
	
	@GetMapping(value = "/registration/concurrency1/{id}", produces = "application/json")
	public String concurrency1(@PathVariable long id) {
		try {
			//registrationRepository.concurrency1(id);
			Registration registration = new Registration();
			registration.setId(id);
			registration.setName("Concurrency1");
			registration.setLastname("Concurrency1");
			registrationRepository.concurrency1(registration);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.info("[RegistrationController::concurrency1] KO");
			return "OptimisticLockException";
		}
		return "Concurrency1 OK";
	}
	
	@GetMapping(value = "/registration/concurrency2/{id}", produces = "application/json")
	public String concurrency2(@PathVariable long id) {
		//registrationRepository.concurrency2(id);
		Registration registration = new Registration();
		registration.setId(id);
		registration.setName("Concurrency2");
		registration.setLastname("Concurrency2");
		registrationRepository.concurrency2(registration);
		return "Concurrency2 OK";
	}
}
