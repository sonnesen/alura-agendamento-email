package br.com.alura.mapper;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ModelMapperFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@Named("modelMapper")
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}
}
