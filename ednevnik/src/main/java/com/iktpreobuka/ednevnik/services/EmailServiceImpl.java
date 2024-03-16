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
		
		String text = "<!DOCTYPE html><html><body>" + "<style>table {\r\n" + "  font-family: arial, sans-serif;\r\n"
				+ "  border-collapse: collapse;\r\n" + "  width: 100%;\r\n" + "}\r\n" + "\r\n" + "td, th {\r\n"
				+ "  border: 1px solid #dddddd;\r\n" + "  text-align: left;\r\n" + "  padding: 8px;\r\n" + "}\r\n"
				+ "\r\n" + "tr:nth-child(even) {\r\n" + "  background-color: #dddddd;\r\n" + "}\r\n" + "</style>\r\n"
				+ "</head>" + "<body>\r\n" + "\r\n" + "<h2>Nova ocena</h2>\r\n" + "\r\n" + "<table>\r\n" + "  <tr>"
				+ "<th>Učenik</th>" + "<th>Predmet</th>" + "<th>Ocena</th>" + "<th>Nastavnik</th>\r\n" + "  </tr>"
				+ "<tr><td>" + ocena.getUcenik().getIme() + " " + ocena.getUcenik().getPrezime()
				+ "</td><td>" + ocena.getPredmet().getNazivPredmeta() + "</td><td>" + ocena.getVrednostOcene() + "</td><td>"
				+ ocena.getAktivnost() + " " + ocena.getOcenjivac() + "</td></tr>" + "</table></body></html>";
		helper.setText(text, true);
		emailSender.send(mail);
	}

}
