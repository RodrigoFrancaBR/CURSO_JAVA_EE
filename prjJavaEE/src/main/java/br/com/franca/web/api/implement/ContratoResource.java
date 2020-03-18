package br.com.franca.web.api.implement;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.business.ContratoBusiness;
import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.domain.Contrato;
import br.com.franca.domain.Parcela;
import br.com.franca.web.api.interfaces.ContratoAPI;

@Path("contratos")

public class ContratoResource extends ResourceGeneric<Contrato> implements ContratoAPI {

	private ContratoBusiness business = new ContratoBusiness();

	@Override
	public Response findAll() {
		List<Contrato> resposta = null;
		try {

			resposta = business.findAll();

			return Response.ok(resposta).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response find(Long id) {
		Contrato resposta = null;
		try {

			resposta = this.business.find(id);

			if (domainIsNull(resposta)) {
				// throw new WebApplicationException(404);
				// throw new CustomNotFoundException("Contrato, " + resposta + ", is not
				// found");
				return Response.status(Status.NOT_FOUND).entity(resposta).build();
			}

			// return Response.ok(resposta).build();
			return Response.status(Status.OK).entity(resposta).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response simularContrato(Contrato contrato) {
		List<Parcela> resposta = null;
		try {
			resposta = this.business.simularContrato(contrato);
			return Response.status(Status.OK).entity(resposta).build();
		} catch (CursoServiceException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response save(Contrato contrato) {
		Contrato resposta = null;

		try {

			resposta = this.business.save(contrato);

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}

		try {

			URI uri = new URI(getUri("contratos/") + resposta.getId());

			return Response.created(uri).entity(resposta).type(MediaType.APPLICATION_JSON_TYPE).build();

		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(resposta).build();
		}
		// return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Não foi possível
		// inserir a contrato").build();
	}

	@Override
	public Response update(Contrato contrato) {

		Contrato resposta = null;

		try {

			// resposta = this.business.update(contrato);

			return Response.ok(this.business.update(contrato)).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response delete(Long id) {

		Contrato resposta = null;

		try {
			resposta = this.business.delete(id);
			return Response.ok(resposta).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
		/*
		 * return domainIsNull(resposta) ?
		 * Response.status(Status.INTERNAL_SERVER_ERROR).
		 * entity("Não foi possível remover a contrato").build() :
		 * Response.ok(resposta).build();
		 */
	}

}
