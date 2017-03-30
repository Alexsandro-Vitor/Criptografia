package funcoes;

public class Deslocamento {
	public static String deslocamento(String entrada, int shift, int varShift, int limite) {
		String saida = "";
		for (int i = 0; i < entrada.length(); i++) {
			saida += trocaChar(entrada.charAt(i), shift + (varShift * i) % limite);
		}
		return saida;
	}
	
	private static char trocaChar(char entrada, int shift) {
		if (minuscula(entrada)) return trocaMinuscula(entrada, shift);
		if (maiuscula(entrada)) return trocaMaiuscula(entrada, shift);
		return trocaForm(entrada, shift);
	}
	
	private static boolean minuscula(char entrada) {
		return (entrada >= 'a' && entrada <= 'z');
	}
	
	private static boolean maiuscula(char entrada) {
		return (entrada >= 'A' && entrada <= 'Z');
	}
	
	private static char trocaMinuscula(char entrada, int shift) {
		shift %= 52;
		entrada = (char)(entrada + shift);
		if (minuscula(entrada)) return entrada;
		if (entrada - 'a' < 0) entrada += 52;
		return (char)('A' + (entrada - 'a') % 26);
	}
	
	private static char trocaMaiuscula(char entrada, int shift) {
		shift %= 52;
		entrada = (char)(entrada + shift);
		if (maiuscula(entrada)) return entrada;
		if (entrada - 'A' < 0) entrada += 52;
		return (char)('a' + (entrada - 'A') % 26);
	}
	
	private static char trocaForm(char entrada, int shift) {
		entrada = (char)(entrada + shift);
		while (maiuscula(entrada) || minuscula(entrada)) {
			entrada += 26;
		}
		return entrada;
	}
}
