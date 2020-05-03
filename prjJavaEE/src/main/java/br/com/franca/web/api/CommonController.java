package br.com.franca.web.api;

public abstract class CommonController {
	protected String endPoint;
	protected String uri = "/curso/";

	public String getUri(String endPoint) {
		return this.uri + endPoint;
	}
}
