package com.iktpreobuka.ednevnik.services;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

//import com.iktpreobuka.ednevnik.entities.NastavnikEntity;
import com.iktpreobuka.ednevnik.entities.OcenaEntity;
//import com.iktpreobuka.ednevnik.repositories.NastavnikRepository;

import jakarta.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl implements EmailService{

//	@Autowired
//	private NastavnikRepository nastavnikRepository;
	
	@Autowired
	public JavaMailSender emailSender;
	
	@Override
	public void posaljiMejlRoditelju(OcenaEntity ocena) throws Exception {
//		String korisnickoIme = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//		Optional<NastavnikEntity> nastavnik = nastavnikRepository.findByKorisnikNastavnikKorisnickoIme(korisnickoIme);
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		String testEmail = "dijana.jovanovic.brains23@gmail.com";
		helper.setTo(/*"Nova ocena"*/testEmail);
		//helper.setTo(ocena.getUcenik().getRoditelj().getEmail());
		helper.setSubject("Nova ocena");
		
		String imePrezimeUcenika = ocena.getUcenik().getIme() + " " + ocena.getUcenik().getPrezime();
	    String imePrezimeNastavnika = ocena.getOcenjivac().getIme() + " " + ocena.getOcenjivac().getPrezime(); // Pretpostavljam da imate metode getIme() i getPrezime()

	    String text = "<!DOCTYPE html>"
	            + "<html>"
	            + "<head>"
	            + "<style>"
	            + "body {font-family: 'Arial', sans-serif; line-height: 1.6; margin: 0; padding: 20px; color: #333;}"
	            + "h2 {color: #0056b3;}"
	            + "table {width: 100%; border-collapse: collapse;}"
	            + "th, td {border: 1px solid #dddddd; text-align: left; padding: 8px;}"
	            + "th {background-color: #f2f2f2;}"
	            + "tr:nth-child(even) {background-color: #f9f9f9;}"
	            + "</style>"
	            + "</head>"
	            + "<body>"
	            + "<h2>Nova ocena za vaše dete</h2>"
	            + "<table>"
	            + "<tr><th>Učenik</th><th>Predmet</th><th>Ocena</th><th>Aktivnost</th><th>Nastavnik</th></tr>"
	            + "<tr><td>" + imePrezimeUcenika + "</td>"
	            + "<td>" + ocena.getPredmet().getNazivPredmeta() + "</td>"
	            + "<td>" + ocena.getVrednostOcene() + "</td>"
	            + "<td>" + ocena.getAktivnost() + "</td>"
	            + "<td>" + imePrezimeNastavnika + "</td></tr>"
	            + "</table>"
	            + "</body>"
	            + "</html>";

	    helper.setText(text, true);
	    emailSender.send(mail);
	}

}
