package io.gitHub.AugustoMello09.validadorCredito.execptions;

public class DadosClienteNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public DadosClienteNotFoundException() {
		super("Dados do cliente como CPF informado não encotrado no sistema");
	}

	
}
