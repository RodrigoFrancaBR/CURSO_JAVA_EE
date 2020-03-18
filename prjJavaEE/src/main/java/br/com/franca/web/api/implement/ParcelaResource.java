package br.com.franca.web.api.implement;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.franca.business.ParcelaBusiness;
import br.com.franca.business.exceptions.CursoServiceException;
import br.com.franca.domain.Parcela;
import br.com.franca.web.api.interfaces.ParcelaAPI;

@Path("parcelas")

public class ParcelaResource extends ResourceGeneric<Parcela> implements ParcelaAPI {

	private ParcelaBusiness business = new ParcelaBusiness();

	@Override
	public Response findAll() {
		List<Parcela> resposta = null;
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
		Parcela resposta = null;
		try {

			resposta = this.business.find(id);

			if (domainIsNull(resposta)) {
				// throw new WebApplicationException(404);
				// throw new CustomNotFoundException("Parcela, " + resposta + ", is not found");
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
	public Response insert(Parcela parcela) {
		Parcela resposta = null;

		try {

			resposta = this.business.insert(parcela);

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}

		try {

			URI uri = new URI(getUri("parcelas/") + resposta.getId());

			return Response.created(uri).entity(resposta).type(MediaType.APPLICATION_JSON_TYPE).build();

		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(resposta).build();
		}
		// return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Não foi possível
		// inserir a parcela").build();
	}

	@Override
	public Response update(Parcela parcela) {

		Parcela resposta = null;

		try {

			// resposta = this.business.update(parcela);

			return Response.ok(this.business.update(parcela)).build();

		} catch (CursoServiceException ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(resposta).build();
		}
	}

	@Override
	public Response delete(Long id) {

		Parcela resposta = null;

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
		 * entity("Não foi possível remover a parcela").build() :
		 * Response.ok(resposta).build();
		 */
	}
	/*
	 * @Override public Response simularContrato(Contrato contrato) { List<Parcela>
	 * resposta = null; try { resposta = this.business.simularParcelas(contrato);
	 * return Response.status(Status.OK).entity(resposta).build(); } catch
	 * (CursoServiceException e) { e.printStackTrace(); return
	 * Response.status(Status.BAD_REQUEST).entity(resposta).build(); } }
	 */

	/*
	 * @Override public Response inserir(Contrato contrato) { List<Parcela> resposta
	 * = null; try { resposta = this.business.inserir(contrato); return
	 * Response.status(Status.OK).entity(resposta).build(); } catch
	 * (CursoServiceException e) { e.printStackTrace(); return
	 * Response.status(Status.BAD_REQUEST).entity(resposta).build(); } }
	 */
}
