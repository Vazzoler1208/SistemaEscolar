package br.com.escola.service;

import br.com.escola.model.Aluno;

//acessar a variavel texto da classe menu e colocar a informação filtrada
public class Regras {
	Aluno infoAluno = new Aluno();
	String letras; //letras
	String especial; //especiais
	String num; // numeros
	String resultado;
	int posicaoEspaco;
	char letraAtual;
	char[] arraySeparador;
	
	public Boolean validarNome(String texto) {
		 letras = texto.replaceAll("[^a-zA-Zá-úÁ-Ú\\s]", ""); //tudo que não for letra é excluido
		 letras = letras.toLowerCase();
		 
		 if (letras.isEmpty() == true)  {
			 System.out.println("Não existem letras no nome!");
			 return false;
		 }
		 
		 arraySeparador = letras.toCharArray();
		 StringBuilder nomeFormatado = new StringBuilder();
		 letraAtual = arraySeparador[0];
		 arraySeparador[0] = Character.toUpperCase(letraAtual);
		 
		 for (int i = 0; i < letras.length(); i++) {
			if (arraySeparador[i] == ' ' && i + 1 < arraySeparador.length) {
				if (Character.isLetter(arraySeparador[i + 1])) {
					arraySeparador[i + 1] = Character.toUpperCase(arraySeparador[i + 1]);
				}
			}
			nomeFormatado.append(arraySeparador[i]);
			
		 }
		 
		 resultado = nomeFormatado.toString();
		 return true;
		
	}
	
	public Boolean validarRg(String texto) {
		num = texto.replaceAll("\\D", "");
		
		if (num.isEmpty() == true) {
			System.out.println("Não existem números no RG!");
			return false;
		}
		else if (num.length() > 10) {
			System.out.println("O RG tem número a mais!");
			return false;
		}
		else if (num.length() < 9) {
			System.out.println("O RG tem número faltando!");
			return false;
		}
		
		arraySeparador = num.toCharArray();
		StringBuilder rgOrganizado = new StringBuilder();
		
		for (int i = 0; i < num.length(); i++) {
			rgOrganizado.append(arraySeparador[i]);
			
			if(i == 1 || i == 4) {
				rgOrganizado.append(".");
			}
			else if (i == 7) {
				rgOrganizado.append("-");
			}
			
		}
		
		resultado = rgOrganizado.toString();
		return true;
		
	}
	
	
	public Boolean validarCpf(String texto)  {
		num = texto.replaceAll("\\D", ""); //numeros
		
		//validações
		if(num.isEmpty() == true) {
			System.out.println("Seu CPF não está no formato correto");
			return false;
		}
		else if (num.length() > 11) {
			System.out.println("O CPF tem número a mais!");
			return false;
		}
		else if (num.length() < 11) {
			System.out.println("Falta número no CPF!");
			return false;
		}
		
		//organizar o cpf
		arraySeparador = num.toCharArray();
		StringBuilder cpfOrganizado = new StringBuilder();
		
		for (int i = 0; i < 11;  i++) {
			cpfOrganizado.append(arraySeparador[i]);
			
			if (i == 2 || i == 5 ) {
				cpfOrganizado.append(".");
			}
			else if (i == 8)  {
				cpfOrganizado.append("-");
			}
			
		}
		
		resultado = cpfOrganizado.toString();
		return true;
		
	}
	
	//TODO: quando tiver o intervalo de mais de um espaço entre um nome e outro, retirar esse espaço extra
	//TODO: todo endereço deve conter o número da casa
	public Boolean validarEndereco(String texto) {
		StringBuilder numerosRua = new StringBuilder();
		letras = texto.replaceAll("[^a-zA-Z0-9á-úÁ-Úà-ùâ-ûã-õçÇ\\s]", "");
		letras = letras.toLowerCase();
		arraySeparador = letras.toCharArray();
		StringBuilder ruaOrganizado = new StringBuilder();
		
		if(letras.matches("\\d+")) {
			System.out.println("Seu endereço deve conter nome!");
			return false;
		}
		
		try {			
			if (Character.isLetter(arraySeparador[0])) {
				arraySeparador[0] = Character.toUpperCase(arraySeparador[0]);
			}
			
			for (int i = 0; i < letras.length(); i++) {
				
				if (Character.isDigit(arraySeparador[i]) == true) {
					numerosRua.append(arraySeparador[i]);
				}
				
				if (i + 1 < arraySeparador.length && (arraySeparador[i] == ' ' || Character.isDigit(arraySeparador[i]))) {
					arraySeparador[i + 1] = Character.toUpperCase(arraySeparador[i + 1]);		
				}
				
				if (Character.isLetter(arraySeparador[i]) || arraySeparador[i] == ' ') {
					ruaOrganizado.append(arraySeparador[i]);
				}
				
			}
			
			ruaOrganizado.append(' ');
			for (int i = 0; i < numerosRua.length(); i++) {
				ruaOrganizado.append(numerosRua.charAt(i));
			}
			
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("ERRO! INSIRA NOVAMENTE");
			System.out.println(e);
			return false;
		}
		
		resultado = ruaOrganizado.toString();
		return true;
	}
	
	public Boolean validarEmail(String texto) {
		arraySeparador = texto.toCharArray();
		int etapa = 0;

		if(arraySeparador[0] == '@') {
			System.out.println("Reprovado");
			return false;
		}
		
		for (int i = 0; i < arraySeparador.length; i++) {
			if (i + 1 < arraySeparador.length && arraySeparador[i] == '@' && Character.isLetter(arraySeparador[i + 1])) {
				etapa = 1;
			}
			
			if (etapa == 1 && i + 1 < arraySeparador.length && Character.isLetter(arraySeparador[i]) && arraySeparador[i + 1] == '.') {
				etapa = 2;
			}
			
			if(etapa == 2 && i + 1 < arraySeparador.length && arraySeparador[i] == '.' && Character.isLetter(arraySeparador[i + 1])) {
				etapa = 3;
			}
		
		}
		
		if(etapa != 3) {
			System.out.println("Insira no formato correto! (ex: nome@tipo.dominio)");	
			return false;
			
		}
		
		resultado = texto;
		return true;
	}
	
	public Boolean validarNumero(String texto) {
		arraySeparador = texto.toCharArray();
		StringBuilder numero = new StringBuilder();

		if (texto == null || texto.isEmpty()) {
			System.out.println("Número inválido");
			return false;
		}
		
		for (int i = 0; i < arraySeparador.length; i++) {
			if(Character.isDigit(arraySeparador[i])) {
				numero.append(arraySeparador[i]);
			}
			
		}
		
		if (numero.length() < 11 || numero.length() > 11) {
			System.out.println("O formato está errado");
			return false;
		}
		

		
		numero.insert(7, "-");
		numero.insert(2, ") ");
		numero.insert(0, "(");
		resultado = numero.toString();
		return true;
		
	}
	
	public String infoFiltrada() {
		return resultado;
	}
	
}
