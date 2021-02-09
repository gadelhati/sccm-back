package br.com.fattoria.sccm.api;

import java.util.List;

public class WrapperListApi<T> {
	
	public WrapperListApi() {
	}

	public WrapperListApi(List<T> content) {
		super();
		this.content = content;
	}

	private List<T> content;

	public List<T> getContent() {
		return content;
	}

}
