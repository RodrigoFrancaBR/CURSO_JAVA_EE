package br.com.franca.web.api;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.exceptions.CursoServiceException;
import br.com.franca.service.ContratoService;

public class ContratoController extends CommonController implements ContratoAPI {

	@Inject
	private ContratoService service;

	@Override
	public Response findAll() {
		try {
			List<Contrato> resposta = service.findAll();

			if (resposta.size() == 0)
				return Response.status(Status.NOT_FOUND).entity(resposta).build();

			return Response.status(Status.OK).entity(resposta).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response findById(Long id) {

		try {
			Contrato resposta = service.findById(id);

			if (resposta == null)
				return Response.status(Status.NOT_FOUND).entity(id).build();

			return Response.status(Status.OK).entity(resposta).build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response simularContrato(Contrato contrato) {

		try {
			List<Parcela> resposta = service.simularContrato(contrato);
			return Response.status(Status.OK).entity(resposta).build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response save(Contrato contrato) {

		try {
			Contrato resposta = service.save(contrato);

			URI uri = new URI(getUri("contratos/") + resposta.getId());
			return Response.created(uri).entity(resposta).type(MediaType.APPLICATION_JSON_TYPE).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response delete(Long id) {

		try {
			service.delete(id);
			return Response.status(Status.OK).build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

}
