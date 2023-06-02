/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plp;

import Objetos.ValBoolean;
import Objetos.ValDouble;
import Objetos.ValInteger;
import Objetos.Variavel;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorRepeticao implements ManipuladorLua {

	private List<Variavel> ListaOV = new ArrayList<Variavel>();
	private List<String> w = new ArrayList<String>();
	private ManipuladorVariavel mv = new ManipuladorVariavel();
	private ManipuladorSaida ms = new ManipuladorSaida();
	ManipuladorLua h = null;

	@Override
	public void addline(String line) {

	}

	public void loadBloco(List<String> a) {
		loadVal();
		boolean c;
		String linhaWhile = a.get(0), bloco;
		linhaWhile = linhaWhile.replaceAll(" ", "");
		linhaWhile = linhaWhile.substring(linhaWhile.indexOf("e") + 1, linhaWhile.indexOf("do"));
		String[] parts;
		// fazer quando for true, false, uma variavel
		do {

			if (linhaWhile.contains("and")) {
				parts = linhaWhile.split("and");
				c = condicao(parts[0]) && condicao(parts[1]);
			} else if (linhaWhile.contains("or")) {
				parts = linhaWhile.split("or");
				c = condicao(parts[0]) || condicao(parts[1]);
			} else {
				c = condicao(linhaWhile);
			}
			if (c) {
				for (int i = 1; a.toArray().length > i; i++) {
					bloco = a.get(i);
					bloco = bloco.replaceAll(" ", "");
					if (bloco.startsWith("break")) {
						continue;
					} else if (bloco.startsWith("do")) {
						continue;
					} else if (bloco.startsWith("else")) {
						// token
						continue;
					} else if (bloco.startsWith("elseif")) {
						// token
						continue;
					} else if (bloco.startsWith("end")) {
						// token
						continue;
					} else if (bloco.startsWith("print")) {
						h = ms;

					} else if (bloco.startsWith("for")) {
						continue;
					} else if (bloco.startsWith("function")) {
						continue;
					} else if (bloco.startsWith("if")) {
						// token
						continue;
					} else if (bloco.startsWith("in")) {
						continue;
					} else if (bloco.startsWith("local")) {
						continue;
					} else if (bloco.startsWith("nil")) {
						// token
						continue;
					} else if (bloco.startsWith("not")) {
						// token
						continue;

					} else if (bloco.startsWith("repeat")) {
						continue;
					} else if (bloco.startsWith("return")) {
						continue;

					} else if (bloco.startsWith("while")) {

						continue;
					} else {
						h = mv;
						// variavel
					}
					h.addline(bloco);
				}
			}

		} while (c);
		a.clear();
	}

	public void loadVal() {
		ListaOV = LeitorDeLua.getListaOV();

	}

	public boolean maiorQ(String a, String b) {
		double aux1 = 0, aux2 = 0;
		ValDouble v1, v2;
		ValInteger v3, v4;

		if (Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			aux2 = Double.parseDouble(b);
			return aux1 > aux2;

		} else if (!Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux2 = Double.parseDouble(b);
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}

					break;
				}

			}
			return aux1 > aux2;

		} else if (Character.isDigit(a.charAt(0)) && !Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}

					break;
				}
			}
			return aux1 > aux2;
		} else {
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}
					break;
				}
			}
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}
					break;
				}
			}
			return aux1 > aux2;
		}

	}

	public boolean menorQ(String a, String b) {
		double aux1 = 0, aux2 = 0;
		ValDouble v1, v2;
		ValInteger v3, v4;

		if (Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			aux2 = Double.parseDouble(b);
			return aux1 < aux2;

		} else if (!Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux2 = Double.parseDouble(b);
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}

					break;
				}

			}
			return aux1 < aux2;

		} else if (Character.isDigit(a.charAt(0)) && !Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}
					break;
				}
			}
			return aux1 < aux2;
		} else {
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}
					break;
				}
			}
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}
					break;
				}
			}
			return aux1 < aux2;
		}

	}

	public boolean maiorQigual(String a, String b) {
		double aux1 = 0, aux2 = 0;
		ValDouble v1, v2;
		ValInteger v3, v4;

		if (Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			aux2 = Double.parseDouble(b);
			return aux1 >= aux2;

		} else if (!Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux2 = Double.parseDouble(b);
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}

					break;
				}

			}
			return aux1 >= aux2;

		} else if (Character.isDigit(a.charAt(0)) && !Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}

					break;
				}
			}
			return aux1 >= aux2;
		} else {
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}
					break;
				}
			}
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}
					break;
				}
			}
			return aux1 >= aux2;
		}

	}

	public boolean menorQigual(String a, String b) {
		double aux1 = 0, aux2 = 0;
		ValDouble v1, v2;
		ValInteger v3, v4;

		if (Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			aux2 = Double.parseDouble(b);
			return aux1 <= aux2;

		} else if (!Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux2 = Double.parseDouble(b);
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}

					break;
				}

			}
			return aux1 <= aux2;

		} else if (Character.isDigit(a.charAt(0)) && !Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}

					break;
				}
			}
			return aux1 <= aux2;
		} else {
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}
					break;
				}
			}
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}
					break;
				}
			}
			return aux1 <= aux2;
		}

	}

	public boolean igual(String a, String b) {
		double aux1 = 0, aux2 = 0;
		ValDouble v1, v2;
		ValInteger v3, v4;
		ValBoolean v5;

		if (Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			aux2 = Double.parseDouble(b);
			return aux1 == aux2;

		} else if (!Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux2 = Double.parseDouble(b);
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}

					break;
				}

			}
			return aux1 == aux2;

		} else if (Character.isDigit(a.charAt(0)) && !Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}

					break;
				}
			}
			return aux1 == aux2;
		} else if (!Character.isDigit(a.charAt(0)) && (b.contains("true") || b.contains("false"))) {
			boolean au;
			au = Boolean.parseBoolean(b);
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("boolean") == 0) {
						v5 = (ValBoolean) ListaOV.get(k);
						return au == v5.isValor();
					}

					break;
				}

			}
			return false;

		} else {
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}
					break;
				}
			}
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}
					break;
				}
			}
			return aux1 == aux2;
		}

	}

	public boolean diferente(String a, String b) {
		double aux1 = 0, aux2 = 0;
		ValDouble v1, v2;
		ValInteger v3, v4;
		ValBoolean v5;

		if (Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			aux2 = Double.parseDouble(b);
			return aux1 != aux2;

		} else if (!Character.isDigit(a.charAt(0)) && Character.isDigit(b.charAt(0))) {
			aux2 = Double.parseDouble(b);
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}

					break;
				}

			}
			return aux1 != aux2;

		} else if (Character.isDigit(a.charAt(0)) && !Character.isDigit(b.charAt(0))) {
			aux1 = Double.parseDouble(a);
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}

					break;
				}
			}
			return aux1 != aux2;
		} else if (!Character.isDigit(a.charAt(0)) && (b.contains("true") || b.contains("false"))) {
			boolean au;
			au = Boolean.parseBoolean(b);
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("boolean") == 0) {
						v5 = (ValBoolean) ListaOV.get(k);
						return au != v5.isValor();
					}

					break;
				}

			}
			return false;

		} else {
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(a) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v1 = (ValDouble) ListaOV.get(k);
						aux1 = v1.getValor();
					} else {
						v3 = (ValInteger) ListaOV.get(k);
						aux1 = (double) v3.getValor();
					}
					break;
				}
			}
			for (int k = 0; ListaOV.toArray().length > k; k++) {

				if (ListaOV.get(k).getNome().compareTo(b) == 0) {
					if (ListaOV.get(k).getTipo().compareTo("Double") == 0) {
						v2 = (ValDouble) ListaOV.get(k);
						aux2 = v2.getValor();
					} else {
						v4 = (ValInteger) ListaOV.get(k);
						aux2 = (double) v4.getValor();
					}
					break;
				}
			}
			return aux1 != aux2;
		}

	}

	public boolean ValBol(String a) {
		ValBoolean v;
		for (int k = 0; ListaOV.toArray().length > k; k++) {

			if (ListaOV.get(k).getNome().compareTo(a) == 0) {
				v = (ValBoolean) ListaOV.get(k);
				return v.isValor();
			}
		}
		return false;
	}

	public boolean condicao(String a) {
		String[] parts;
		if (a.contains(">=")) {
			parts = a.split(">=");
			return maiorQigual(parts[0], parts[1]);
		} else if (a.contains("<=")) {
			parts = a.split("<=");
			return menorQigual(parts[0], parts[1]);
		} else if (a.contains(">")) {
			parts = a.split(">");
			return maiorQ(parts[0], parts[1]);
		} else if (a.contains("<")) {
			parts = a.split("<");
			return menorQ(parts[0], parts[1]);
		} else if (a.contains("==")) {
			parts = a.split("==");
			return igual(parts[0], parts[1]);
		} else if (a.contains("~=")) {
			parts = a.split("~=");
			return diferente(parts[0], parts[1]);
			// dados de condicao
		} else if (a.contains("true")) {
			return true;
		} else if (a.contains("false")) {
			return false;
			// se for apenas true or false
		} else {
			return ValBol(a);
			// se for apenas uma variavel
		}
	}
}
