package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import servidor.DepartamentoNoExisteException_Exception;
import servidor.PublicadorDepartamentos;
import servidor.PublicadorDepartamentosService;

public class DaoDepartamentos {

	private PublicadorDepartamentosService publicadorDepartamentosService;
	private PublicadorDepartamentos publicadorDepartamentos;

	public DaoDepartamentos() {
		publicadorDepartamentosService = new PublicadorDepartamentosService();
		publicadorDepartamentos = publicadorDepartamentosService.getPublicadorDepartamentosPort();
	}

	public List<String> obtenerDepartamentos() {
		List<String> listaDepartamentos = new ArrayList<String>();
		try {
			List<servidor.DataDepartamento> departamentos = publicadorDepartamentos.obtenerDepartamentos().getItem();
			for (servidor.DataDepartamento depto : departamentos) {
				listaDepartamentos.add(depto.getNombre());
			}
		} catch (DepartamentoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
		}
		return listaDepartamentos.stream().sorted().collect(Collectors.toList());
	}

}
