package io.gitHub.AugustoMello09.validadorCredito.execptions;

import lombok.Getter;

public class ErroComunicacaoMicrosserviceException extends Exception {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer status;
	
	public ErroComunicacaoMicrosserviceException(String msg, Integer status) {
		super(msg);
		this.status = status;
	}

	
}
