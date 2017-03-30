package funcoes;

public class Embaralhamento {
	public static String embaralhar(String entrada, int codigo, byte limite, boolean embaralhando) {
		codigo %= fatorial(limite);
		byte[] ordem = gerarOrdem(codigo, limite);
		if (!embaralhando) ordem = reordenar(ordem);
		String saida = "";
		for (int i = 0; i < entrada.length(); i++) {
			if ((i+1) * limite <= entrada.length()) saida += embaralhaGrupo(entrada.substring(i*limite, (i+1)*limite), ordem);
		}
		saida += entrada.substring(entrada.length() - (entrada.length() % limite));
		return saida;
	}	

	public static int fatorial(int i) {
		return (i > 1) ? i * fatorial(i-1) : 1;
	}
	
	//Gera um array de bytes indicando em que ordem cada caractere deve ser colocado
	private static byte[] gerarOrdem(int codigo, byte limite) {
		byte[] ordem = new byte[limite];
		boolean[] escolhido = new boolean[limite];
		for (byte i = 0; i < limite; i++) {
			int proximo = (codigo / fatorial(limite - i - 1));
			for (byte j = 0; ; j++) {
				if (!escolhido[j]) {
					if (proximo == 0) {
						ordem[i] = j;
						escolhido[j] = true;
						break;
					} else proximo--;
				}
			}
			codigo %= fatorial(limite - i - 1);
		}
		return ordem;
	}
	
	private static byte[] reordenar(byte[] entrada) {
		byte[] saida = new byte[entrada.length];
		for (byte i = 0; i < entrada.length; i++) {
			saida[entrada[i]] = i;
		}
		return saida;
	}
	
	private static String embaralhaGrupo(String entrada, byte[] ordem) {
		String saida = "";
		for (int i = 0; i < ordem.length; i++) {
			saida += entrada.charAt(ordem[i]);
		}
		return saida;
	}
}