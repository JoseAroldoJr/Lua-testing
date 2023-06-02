package plp;

import Objetos.ValDouble;
import Objetos.ValInteger;
import Objetos.Variavel;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorSaida implements ManipuladorLua {

	private List<Variavel> ListaOV = new ArrayList<Variavel>();

	public void loadVal() {
		ListaOV = LeitorDeLua.getListaOV();

	}

	@Override
	public void addline(String line) {
		loadVal();
		String linhaPrint = line;
		linhaPrint = linhaPrint.substring(linhaPrint.indexOf("(") + 1, linhaPrint.indexOf(")"));
		if (linhaPrint.contains("\'") || linhaPrint.contains("\"")) {
			System.out.println(
					(linhaPrint.contains("\'") ? linhaPrint.replaceAll("\'", "") : linhaPrint.replaceAll("\"", "")));
			// se for um string direto ele printa
		} else if (!Character.isDigit(linhaPrint.charAt(0))) {
			linhaPrint = linhaPrint.replaceAll(" ", "");
			Variavel aux = null;
			if (linhaPrint.contains("*") || linhaPrint.contains("+") || linhaPrint.contains("/")
					|| linhaPrint.contains("-") || Character.isDigit(linhaPrint.charAt(0))) {
				if (linhaPrint.contains(".")) {
					if (linhaPrint.contains("*")) {
						aux = multiplicacao(linhaPrint);

						// codigo de multiplicacao
					} else if (linhaPrint.contains("/")) {
						aux = divisao(linhaPrint);

						// codigo de divisao
					} else if (linhaPrint.contains("+")) {
						aux = soma(linhaPrint);

						// codigo de soma
					} else if (linhaPrint.contains("-")) {
						aux = subtracao(linhaPrint);

						// codigo de subtracao
					} else {
						aux = new ValDouble(Double.parseDouble(linhaPrint), "Double", " ");

					}
				} else {
					if (linhaPrint.contains("*")) {
						aux = multiplicacaoInteiro(linhaPrint);

						// codigo de multiplicacao
					} else if (linhaPrint.contains("/")) {
						aux = divisaoInteiro(linhaPrint);

						// codigo de divisao
					} else if (linhaPrint.contains("+")) {
						aux = somaInteiro(linhaPrint);

						// codigo de soma
					} else if (linhaPrint.contains("-")) {
						aux = subtracaoInteiro(linhaPrint);

						// codigo de subtracao
					} else {
						aux = new ValInteger(Integer.parseInt(linhaPrint), "Integer", "");

					}
				}
				// faz operacoes basicas aritmetricas e testa se eh um numero ou operacao
			} else {
				for (int j = 0; ListaOV.toArray().length > j; j++) {
					if (ListaOV.get(j).getNome().compareTo(linhaPrint) == 0) {
						aux = ListaOV.get(j);
					}
				}
				// testa se o valor vem de uma variavel
			}

			System.out.println(aux);
			// separaca£o de tipos
			// se nao for um digito sera uma variavel
		} else {
			// se for um digito printa direto
			System.out.println(linhaPrint);

		}

	}

	public ValDouble multiplicacao(String i) {
		double m = 1;
		String[] Multi = i.split("\\*");
		for (int j = 0; Multi.length > j; j++) {
			if (Character.isDigit(Multi[j].charAt(0))) {
				m = m * Double.parseDouble(Multi[j]);
			} else {

				for (int k = 0; ListaOV.toArray().length > k; k++) {

					if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
						ValDouble a = (ValDouble) ListaOV.get(k);
						m = m * a.getValor();
						break;
					}
				}
			}
		}
		ValDouble aux = new ValDouble(m, "Double", " ");
		return aux;
	}

	public ValDouble soma(String i) {
		double m = 0;
		String[] Multi = i.split("\\+");
		for (int j = 0; Multi.length > j; j++) {
			if (Character.isDigit(Multi[j].charAt(0))) {
				m = m + Double.parseDouble(Multi[j]);
			} else {

				for (int k = 0; ListaOV.toArray().length > k; k++) {

					if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
						ValDouble a = (ValDouble) ListaOV.get(k);
						m = m + a.getValor();
						break;
					}
				}
			}
		}
		ValDouble aux = new ValDouble(m, "Double", "");
		return aux;
	}

	public ValDouble subtracao(String i) {
		String[] Multi = i.split("-");
		double m = 0;
		if (!Multi[0].isEmpty() && Character.isAlphabetic(Multi[0].charAt(0))) {
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(Multi[0]) == 0) {
					ValDouble a = (ValDouble) ListaOV.get(k);
					m = a.getValor();
					break;
				}
			}
			for (int j = 1; Multi.length > j; j++) {
				if (Character.isDigit(Multi[j].charAt(0))) {
					m = m - Double.parseDouble(Multi[j]);
				} else {

					for (int k = 0; ListaOV.toArray().length > k; k++) {

						if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
							ValDouble a = (ValDouble) ListaOV.get(k);
							m = m - a.getValor();
							break;
						}
					}
				}
			}
			ValDouble aux = new ValDouble(m, "Double", i);
			return aux;
		} else if (!Multi[0].isEmpty()) {
			m = Double.parseDouble(Multi[0]);

			for (int j = 1; Multi.length > j; j++) {
				if (Character.isDigit(Multi[j].charAt(0))) {
					m = m - Double.parseDouble(Multi[j]);
				} else {

					for (int k = 0; ListaOV.toArray().length > k; k++) {

						if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
							ValDouble a = (ValDouble) ListaOV.get(k);
							m = m - a.getValor();
							break;
						}
					}
				}
			}
			ValDouble aux = new ValDouble(m, "Double", " ");
			return aux;
		} else {
			m = -Double.parseDouble(Multi[1]);

			for (int j = 2; Multi.length > j; j++) {
				if (Character.isDigit(Multi[j].charAt(0))) {
					m = m - Double.parseDouble(Multi[j]);
				} else {

					for (int k = 0; ListaOV.toArray().length > k; k++) {

						if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
							ValDouble a = (ValDouble) ListaOV.get(k);
							m = m - a.getValor();
							break;
						}
					}
				}
			}
			ValDouble aux = new ValDouble(m, "Double", "");
			return aux;
		}
	}

	public ValDouble divisao(String i) {
		double m = 0;
		String[] Multi = i.split("\\/");
		if (Character.isAlphabetic(Multi[0].charAt(0))) {
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(Multi[0]) == 0) {
					ValDouble a = (ValDouble) ListaOV.get(k);
					m = a.getValor();
					break;
				}
			}
		} else {
			m = Double.parseDouble(Multi[0]);

		}

		for (int j = 1; Multi.length > j; j++) {
			if (Character.isDigit(Multi[j].charAt(0))) {
				m = m / Double.parseDouble(Multi[j]);
			} else {

				for (int k = 0; ListaOV.toArray().length > k; k++) {

					if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
						ValDouble a = (ValDouble) ListaOV.get(k);
						m = m / a.getValor();
						break;

					}
				}
			}
		}
		ValDouble aux = new ValDouble(m, "Double", "");
		return aux;
	}
	// operacoes com double

	public ValInteger multiplicacaoInteiro(String i) {
		int m = 1;
		String[] Multi = i.split("\\*");
		for (int j = 0; Multi.length > j; j++) {
			if (Character.isDigit(Multi[j].charAt(0))) {
				m = m * Integer.parseInt(Multi[j]);
			} else {

				for (int k = 0; ListaOV.toArray().length > k; k++) {

					if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
						ValInteger a = (ValInteger) ListaOV.get(k);
						m = m * a.getValor();
						break;
					}
				}
			}
		}
		ValInteger aux = new ValInteger(m, "Integer", " ");
		return aux;
	}

	public ValInteger somaInteiro(String i) {
		int m = 0;
		String[] Multi = i.split("\\+");
		for (int j = 0; Multi.length > j; j++) {
			if (Character.isDigit(Multi[j].charAt(0))) {
				m = m + Integer.parseInt(Multi[j]);
			} else {

				for (int k = 0; ListaOV.toArray().length > k; k++) {

					if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
						ValInteger a = (ValInteger) ListaOV.get(k);
						m = m + a.getValor();
						break;

					}
				}
			}
		}
		ValInteger aux = new ValInteger(m, "Integer", "");
		return aux;
	}

	public ValInteger subtracaoInteiro(String i) {
		String[] Multi = i.split("-");
		int m = 0;
		if (!Multi[0].isEmpty() && Character.isAlphabetic(Multi[0].charAt(0))) {
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(Multi[0]) == 0) {
					ValInteger a = (ValInteger) ListaOV.get(k);
					m = a.getValor();
					break;
				}
			}
			for (int j = 1; Multi.length > j; j++) {
				if (Character.isDigit(Multi[j].charAt(0))) {
					m = m - Integer.parseInt(Multi[j]);
				} else {

					for (int k = 0; ListaOV.toArray().length > k; k++) {

						if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
							ValInteger a = (ValInteger) ListaOV.get(k);
							m = m - a.getValor();
							break;
						}
					}
				}
			}
			ValInteger aux = new ValInteger(m, "Integer", "");
			return aux;
		} else if (!Multi[0].isEmpty()) {
			m = Integer.parseInt(Multi[0]);

			for (int j = 1; Multi.length > j; j++) {
				if (Character.isDigit(Multi[j].charAt(0))) {
					m = m - Integer.parseInt(Multi[j]);
				} else {

					for (int k = 0; ListaOV.toArray().length > k; k++) {

						if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
							ValInteger a = (ValInteger) ListaOV.get(k);
							m = m - a.getValor();
							break;
						}
					}
				}
			}
			ValInteger aux = new ValInteger(m, "Integer", "");
			return aux;
		} else {
			m = -Integer.parseInt(Multi[1]);

			for (int j = 2; Multi.length > j; j++) {
				if (Character.isDigit(Multi[j].charAt(0))) {
					m = m - Integer.parseInt(Multi[j]);
				} else {

					for (int k = 0; ListaOV.toArray().length > k; k++) {

						if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
							ValInteger a = (ValInteger) ListaOV.get(k);
							m = m - a.getValor();
							break;
						}
					}
				}
			}
			ValInteger aux = new ValInteger(m, "Integer", "");
			return aux;
		}
	}

	public ValInteger divisaoInteiro(String i) {
		int m = 0;
		String[] Multi = i.split("\\/");
		if (Character.isAlphabetic(Multi[0].charAt(0))) {
			for (int k = 0; ListaOV.toArray().length > k; k++) {
				if (ListaOV.get(k).getNome().compareTo(Multi[0]) == 0) {
					ValInteger a = (ValInteger) ListaOV.get(k);
					m = a.getValor();
					break;
				}
			}
		} else {
			m = Integer.parseInt(Multi[0]);

		}

		for (int j = 1; Multi.length > j; j++) {
			if (Character.isDigit(Multi[j].charAt(0))) {
				m = m / Integer.parseInt(Multi[j]);
			} else {

				for (int k = 0; ListaOV.toArray().length > k; k++) {

					if (ListaOV.get(k).getNome().compareTo(Multi[j]) == 0) {
						ValInteger a = (ValInteger) ListaOV.get(k);
						m = m / a.getValor();
						break;
					}
				}
			}
		}
		ValInteger aux = new ValInteger(m, "Integer", "");
		return aux;

	}

}
