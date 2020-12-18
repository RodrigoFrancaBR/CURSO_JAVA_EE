package br.com.franca.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.franca.dao.DAOGeneric;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;
import br.com.franca.exceptions.CursoDAOException;
import br.com.franca.exceptions.CursoServiceException;
import br.com.franca.msg.Mensagem;

public class UnidadeService extends CommonServiceValidations {

	@Inject
	private DAOGeneric<Unidade> dao;

	public List<Unidade> findAll() throws CursoDAOException {
		if(1>0){
			return dao.findAll();	
		}else{			
		
		/**
		 * Fazendo o papel de cliente
		 */			 
		
		List<Unidade> listaDeUnidades = null;
		
		String urlDoServidor = "http://localhost:8080/prjCursoJaxRsJersey/unidades";
		try {
			
			URL url = new URL(urlDoServidor);
			
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();

			// optional default is GET
			httpUrlConnection.setRequestMethod("GET");
			// httpUrlConnection.setConnectTimeout();
			
			int responseCode = httpUrlConnection.getResponseCode();	
			
			System.out.println("\nSending 'GET' request to URL : " + urlDoServidor);
			System.out.println("Response Code : " + responseCode);

			// Retorna um fluxo de entrada que lê a partir desta conexão aberta.
			// os dados trafegados aqui sáo em bytes?
			InputStream inputStream = httpUrlConnection.getInputStream();
			
			// Um InputStreamReader é uma ponte de fluxos de bytes para fluxos de caracteres: lê bytes e os decodifica em caracteres
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			
			//Lê o texto de um fluxo de entrada de caracteres, armazenando caracteres em buffer para
			// proporcionam a leitura eficiente de caracteres, matrizes e linhas.
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);	
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = bufferedReader.readLine()) != null) {
				response.append(inputLine);
			}
			
			bufferedReader.close();

			// print result in string
			System.out.println(response.toString());			
			
			// JSONObject myresponse = new JSONObject(response.toString());
			// System.out.println(myresponse);

			/*System.out.println("base -" + myresponse.getString("base"));
			System.out.println("date -" + myresponse.getString("date"));
			JSONObject rates_object = new JSONObject(myresponse.getJSONObject("rates").toString());*/
			
			Gson gson= new Gson();
			// Deserialization
			Type ListType = new TypeToken<List<Unidade>>(){}.getType();
			listaDeUnidades = gson.fromJson(response.toString(), ListType);
			System.out.println(listaDeUnidades.toString());																			
			
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDeUnidades;
		
		}

	}

	public Unidade findById(Long id) throws CursoServiceException, CursoDAOException {

		Unidade unidade = dao.fimdById(id);

		if (unidade == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		return unidade;

	}

	public Unidade save(Unidade unidade) throws CursoServiceException, CursoDAOException {

		if (unidade == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_null"));

		if (nomeInvalido(unidade.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_invalido"));

		if (enderecoInvalido(unidade.getEndereco()))
			throw new CursoServiceException(Mensagem.getMessage("endereco_invalido"));

		// aplicar regras de unique

		unidade.setStatus(Status.ATIVA);

		return this.dao.save(unidade);
	}

	public Unidade update(Long id, Unidade unidade) throws CursoServiceException, CursoDAOException {

		Unidade unidadeEncontrada = null;

		if (unidade == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_null"));

		unidadeEncontrada = findById(id);

		if (unidadeEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		if (nomeInvalido(unidade.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_invalido"));

		if (enderecoInvalido(unidade.getEndereco()))
			throw new CursoServiceException(Mensagem.getMessage("endereco_invalido"));

		/*if (statusInvalido(unidade.getStatus()))
			throw new CursoServiceException(Mensagem.getMessage("status_invalido"));*/

		configurarObjetoAntesDeAtualizar(unidade, unidadeEncontrada);

		return this.dao.update(unidadeEncontrada);
	}

	public void delete(Long id) throws CursoServiceException, CursoDAOException {

		Unidade unidadeEncontrada = null;

		unidadeEncontrada = findById(id);

		if (unidadeEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		unidadeEncontrada.setStatus(Status.DESATIVADA);

		dao.delete(unidadeEncontrada);
	}

	private void configurarObjetoAntesDeAtualizar(Unidade unidade, Unidade unidadeEncontrada) {
		unidadeEncontrada.setNome(unidade.getNome());
		unidadeEncontrada.setEndereco(unidade.getEndereco());
		unidadeEncontrada.setStatus(unidade.getStatus());
	}
}
