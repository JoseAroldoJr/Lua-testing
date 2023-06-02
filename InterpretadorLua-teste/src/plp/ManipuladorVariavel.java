package plp;

import Objetos.ValBoolean;
import Objetos.ValDouble;
import Objetos.ValInteger;
import Objetos.ValString;
import Objetos.Variavel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ManipuladorVariavel implements ManipuladorLua, Serializable {

	Map<String, Integer> variaveisInt = new HashMap<String, Integer>();
	private List<String> ListaV = new ArrayList<String>();
	private List<String> ListaR = new ArrayList<String>();
	private List<Variavel> ListaOV = new ArrayList<Variavel>();
	Scanner s = new Scanner(System.in);

	public void loadVal() {
		ListaOV = LeitorDeLua.getListaOV();

	}

	@Override
	public void addline(String line) {
		loadVal();

		line = line.replaceAll(" ", "");
		String[] parts = line.split("=");

		for (int i = 0; parts.length > i; i++) {

			if (i == 0) {
				String[] variaveis = parts[0].split(",");
				for (int j = 0; variaveis.length > j; j++) {
					ListaV.add(variaveis[j]);
				}
			} else {
				String[] valor = parts[1].split(",");
				for (int j = 0; valor.length > j; j++) {
					ListaR.add(valor[j]);
				}
			}
			// Separacao das variaveis de seus resultados
		}

		for (int i = 0; ListaR.toArray().length > i; i++) {

			Variavel aux = null;
			if (ListaR.get(i).contains("io.read")) {
				ListaR.remove(i);
				String usuario = s.nextLine();
				int j = 0;
				for (j = 0; usuario.length() > j; j++) {
					if (!Character.isDigit(usuario.charAt(j))) {
						ListaR.add("\'" + usuario + "\'");
						break;
					}
				}

				if (j == usuario.length()) {
					ListaR.add(usuario);
				}
			}
			// IO e tratamento dos dados
			if (ListaR.get(i).compareTo("nil") == 0) {
				aux = new ValString(null, "null", ListaV.get(i));
				// testa se eh nulo
			} else if (ListaR.get(i).charAt(0) == '\'' || ListaR.get(i).charAt(0) == '\"') {
				aux = new ValString(ListaR.get(i), "String", ListaV.get(i));
				// testa se eh string
			} else if (ListaR.get(i).compareTo("true") == 0) {
				aux = new ValBoolean(true, "boolean", ListaV.get(i));
				// testa se eh boolean true
			} else if (ListaR.get(i).compareTo("false") == 0) {
				aux = new ValBoolean(false, "boolean", ListaV.get(i));
				// testa se eh boolean false

			} else if (ListaR.get(i).contains("*") || ListaR.get(i).contains("+") || ListaR.get(i).contains("/")
					|| ListaR.get(i).contains("-") || Character.isDigit(ListaR.get(i).charAt(0))) {
				if (ListaR.get(i).contains(".")) {
					if (ListaR.get(i).contains("*")) {
						aux = multiplicacao(i);

						// codigo de multiplicacao
					} else if (ListaR.get(i).contains("/")) {
						aux = divisao(i);

						// codigo de divisao
					} else if (ListaR.get(i).contains("+")) {
						aux = soma(i);

						// codigo de soma
					} else if (ListaR.get(i).contains("-")) {
						aux = subtracao(i);

						// codigo de subtracao
					} else {
						aux = new ValDouble(Double.parseDouble(ListaR.get(i)), "Double", ListaV.get(i));

					}
				} else {
					if (ListaR.get(i).contains("*")) {
						aux = multiplicacaoInteiro(i);

						// codigo de multiplicacao
					} else if (ListaR.get(i).contains("/")) {
						aux = divisaoInteiro(i);

						// codigo de divisao
					} else if (ListaR.get(i).contains("+")) {
						aux = somaInteiro(i);

						// codigo de soma
					} else if (ListaR.get(i).contains("-")) {
						aux = subtracaoInteiro(i);

						// codigo de subtracao
					} else {
						aux = new ValInteger(Integer.parseInt(ListaR.get(i)), "Integer", ListaV.get(i));

					}
				}
				// faz operacoes basicas aritmetricas e testa se eh um numero ou operacaoo
			} else {
				for (int j = 0; ListaOV.toArray().length > j; j++) {
					if (ListaOV.get(j).getNome().compareTo(ListaR.get(i)) == 0) {
						aux = ListaOV.get(j);
					}
				}
				// testa se o valor vem de uma variavel
			}

			for (int j = 0; ListaOV.toArray().length > j; j++) {
				if (ListaOV.get(j).getNome().compareTo(aux.getNome()) == 0) {
					ListaOV.remove(j);
				}
			}

			ListaOV.add(aux);
			// separacao de tipos
		}

		ListaR.clear();
		ListaV.clear();
		LeitorDeLua.setListaOV(ListaOV);

	}

	public ValDouble multiplicacao(int i) {
		double m = 1;
		String[] Multi = ListaR.get(i).split("\\*");
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
		ValDouble aux = new ValDouble(m, "Double", ListaV.get(i));
		return aux;
	}

	public ValDouble soma(int i) {
		double m = 0;
		String[] Multi = ListaR.get(i).split("\\+");
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
		ValDouble aux = new ValDouble(m, "Double", ListaV.get(i));
		return aux;
	}

	public ValDouble subtracao(int i) {
		String[] Multi = ListaR.get(i).split("-");
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
			ValDouble aux = new ValDouble(m, "Double", ListaV.get(i));
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
			ValDouble aux = new ValDouble(m, "Double", ListaV.get(i));
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
			ValDouble aux = new ValDouble(m, "Double", ListaV.get(i));
			return aux;
		}
	}

	public ValDouble divisao(int i) {
		double m = 0;
		String[] Multi = ListaR.get(i).split("\\/");
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
		ValDouble aux = new ValDouble(m, "Double", ListaV.get(i));
		return aux;
	}
	// operacoes com double

	public ValInteger multiplicacaoInteiro(int i) {
		int m = 1;
		String[] Multi = ListaR.get(i).split("\\*");
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
		ValInteger aux = new ValInteger(m, "Integer", ListaV.get(i));
		return aux;
	}

	public ValInteger somaInteiro(int i) {
		int m = 0;
		String[] Multi = ListaR.get(i).split("\\+");
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
		ValInteger aux = new ValInteger(m, "Integer", ListaV.get(i));
		return aux;
	}

	public ValInteger subtracaoInteiro(int i) {
		String[] Multi = ListaR.get(i).split("-");
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
			ValInteger aux = new ValInteger(m, "Integer", ListaV.get(i));
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
			ValInteger aux = new ValInteger(m, "Integer", ListaV.get(i));
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
			ValInteger aux = new ValInteger(m, "Integer", ListaV.get(i));
			return aux;
		}
	}

	public ValInteger divisaoInteiro(int i) {
		int m = 0;
		String[] Multi = ListaR.get(i).split("\\/");
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
		ValInteger aux = new ValInteger(m, "Integer", ListaV.get(i));
		return aux;
	}
	// Operacoees com int
}
