package br.unibh.loja.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;
import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.negocio.ServicoCategoria;

public class ConversorCategoria implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		try {
			ServicoCategoria ss = (ServicoCategoria) new InitialContext().lookup("java:module/ServicoCategoria");
			if (value != null)
				return ss.find(new Long(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if (value != null && value instanceof Categoria) {
			Categoria s = (Categoria) value;
			if (s.getId() != null)
				return s.getId().toString();
		}
		return null;
	}
}