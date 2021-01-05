package br.com.fattoria.sccm.persistence.validation;

public class ValidationMessage {

	private String field;

	private String message;
	
	private Object invalidValue;

	public ValidationMessage(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}
	
	public ValidationMessage(String field, String message, Object invalidValue) {
		super();
		this.field = field;
		this.message = message;
		this.invalidValue = invalidValue;
	}



	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getInvalidValue() {
		return invalidValue;
	}

	public void setInvalidValue(Object invalidValue) {
		this.invalidValue = invalidValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidationMessage other = (ValidationMessage) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValidationMessage [field=" + field + ", message=" + message + "]";
	}

}
