package it.polito.tdp.borders.model;

public class Country implements Comparable<Country>{
	
	private String nome;
	private int cod;
	private String abb;
	
	public Country(String nome, int cod, String abb) {
		this.nome = nome;
		this.cod = cod;
		this.abb = abb;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getAbb() {
		return abb;
	}

	public void setAbb(String abb) {
		this.abb = abb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod;
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
		Country other = (Country) obj;
		if (cod != other.cod)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return abb+" - "+nome;
	}

	@Override
	public int compareTo(Country c) {
		return this.nome.compareTo(c.getNome());
	}
	
}
