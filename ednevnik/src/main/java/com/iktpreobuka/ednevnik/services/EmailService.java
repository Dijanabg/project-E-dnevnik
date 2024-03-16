package com.iktpreobuka.ednevnik.services;

import com.iktpreobuka.ednevnik.entities.OcenaEntity;

public interface EmailService {

	void posaljiMejlRoditelju(OcenaEntity ocena) throws Exception ;

}
