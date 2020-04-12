package br.com.franca.web.api;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.domain.Parcela;
import br.com.franca.service.ParcelaService;
import br.com.franca.service.exceptions.CursoServiceException;

public class ParcelaImplementAPI extends WebAPIGeneric<Parcela> implements ParcelaInterfaceAPI {

	@Inject
	private ParcelaService service;

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
	public Response find(Long id) {

		try {
			Parcela resposta = service.find(id);

			if (domainIsNull(resposta)) {
				return Response.status(Status.NOT_FOUND).entity(id).build();
			}

			return Response.status(Status.OK).entity(resposta).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}

	@Override
	public Response save(Parcela parcela) {

		try {
			Parcela resposta = service.save(parcela);
			URI uri = new URI(getUri("parcelas/") + resposta.getId());
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
	public Response update(Parcela parcela) {

		try {
			return Response.ok(service.update(parcela)).build();
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
