package br.com.franca.web.api;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.service.ContratoService;
import br.com.franca.service.exceptions.CursoServiceException;

public class ContratoImplementAPI extends WebAPIGeneric<Contrato> implements ContratoInterfaceAPI {

	@Inject
	private ContratoService service;

	@Override
	public Response findAll() {

		try {
			return Response.ok(service.findAll()).build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response findById(Long id) {

		try {

			Contrato resposta = service.findById(id);

			if (domainIsNull(resposta)) {
				return Response.status(Status.NOT_FOUND).entity(id).build();
			}

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
	public Response update(Contrato contrato) {

		try {
			return Response.ok(service.update(contrato)).build();
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
			return Response.ok().build();
		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

}
