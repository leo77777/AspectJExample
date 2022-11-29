package fr.leo.service;

import org.springframework.stereotype.Service;

import fr.leo.aspects.Log;
import fr.leo.aspects.SecuredByAspect;

@Service
public class MetierImpl implements IMetier {

	@Override
	@Log	
	@SecuredByAspect(roles= {"USER","ADMIN"})
	public void process() {
		System.out.println("Business proces ...");
	}

	@Override
	@Log
	@SecuredByAspect(roles= {"ADMIN"})
	public double compute() {
		double data = 75;
		System.out.println("Business computing and returning");
		return data;
	}

}
