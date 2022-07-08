package br.com.estevam.forum.config.validation;

public class ErrorFormDataDto {

	private String campo;

	private String erro;

	public ErrorFormDataDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
