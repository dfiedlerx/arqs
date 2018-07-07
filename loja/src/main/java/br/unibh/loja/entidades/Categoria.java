package br.unibh.loja.entidades;

public class Categoria {	
	
	private  Long id;
	private  String descricao;
	
	public Categoria () {
		super();
	}
	
	Categoria(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	//Métodos get e set
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	//Métodos hashCode
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		//Métodos equals
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Categoria other = (Categoria) obj;
			if (descricao == null) {
				if (other.descricao != null)
					return false;
			} else if (!descricao.equals(other.descricao))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		//Método toString
		@Override
		public String toString() {
			return "Categoria [id=" + id + ", descricao=" + descricao + "]";
		}
}
