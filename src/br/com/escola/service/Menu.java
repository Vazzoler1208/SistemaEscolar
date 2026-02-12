package br.com.escola.service;

import java.util.Scanner;
import br.com.escola.model.Aluno;

public class Menu {
	Regras validacao = new Regras();
	Scanner scan = new Scanner(System.in);
	Aluno infoAluno = new Aluno(); //cadAluno
	Dados arquivo = new Dados(); //info
	private String textos;
	int contador = 1;

	public void menuPrincipal() {
		int numeros;
		
		// numeros = 0; //zerar o valor para não bugar
		System.out.print("Bem vindo ao sistema escolar! \n========== \nEscolha a função desejada: \n ");
		System.out.println("\n1-Cadastrar aluno \n2-Visualizar registro \n3-Deletar registro \n4-Editar registro");
		
		try {
			numeros = scan.nextInt();
			switch (numeros) {
			case 1:
				menuCadastro();
				break;

			case 2:
				menuVisualizar();
				
			case 3:
				menuDeletar();
			
			case 4:
				menuEditar();
			
			default:
				System.out.println("Essa opção não existe! Digite novamente");
				menuPrincipal();
				break;
			}

		} catch (java.util.InputMismatchException e) {
			System.out.println("INSIRA APENAS NÚMEROS!");
			scan.nextLine();
			menuPrincipal();

		}

	}

	public void menuCadastro() {
		scan.nextLine(); //bug após algum erro de formatação do usuario
		
		try {

			switch (contador) {
			case 1:				
				do {
					System.out.println("Insira o nome: ");
					textos = scan.nextLine();
					
				} while (validacao.validarNome(textos) == false);
				
				textos = validacao.infoFiltrada();
				infoAluno.setNome(textos);
				contador++;
				
			case 2:				
				do {
					System.out.println("Insira o RG: ");
					textos = scan.nextLine();
					
				} while (validacao.validarRg(textos) == false);
				
				textos = validacao.infoFiltrada();
				infoAluno.setRg(textos);
				contador++;

			case 3:				
				do {
					System.out.println("Insira o CPF: ");
					textos = scan.nextLine();
					
				} while (validacao.validarCpf(textos) == false);
				
				textos = validacao.infoFiltrada();
				infoAluno.setCpf(textos);
				contador++;

			case 4:				
				do {
					System.out.println("Insira o endereço: ");
					textos = scan.nextLine();
					
				} while (validacao.validarEndereco(textos) == false);
				
				textos = validacao.infoFiltrada();
				infoAluno.setEndereco(textos);
				contador++;

			case 5:				
				do {
					System.out.println("Insira o nome do pai: ");
					textos = scan.nextLine();
					
				} while (validacao.validarNome(textos) == false);

				textos = validacao.infoFiltrada();
				infoAluno.setPai(textos);
				contador++;

			case 6:				
				do {
					System.out.println("Insira o nome da mãe: ");
					textos = scan.nextLine();
					
				} while (validacao.validarNome(textos) == false);

				textos = validacao.infoFiltrada();
				infoAluno.setMae(textos);
				contador++;

			case 7:
				do {
					System.out.println("Insira o Email do pai: ");
					textos = scan.nextLine();
										
				} while (validacao.validarEmail(textos) == false);

				textos = validacao.infoFiltrada();
				infoAluno.setEmailPai(textos);
				contador++;

			case 8:				
				do {
					System.out.println("Insira o Email da mãe: ");
					textos = scan.nextLine();
					
				} while (validacao.validarEmail(textos) == false);
				
				textos = validacao.infoFiltrada();
				infoAluno.setEmailMae(textos);
				contador++;

			case 9:				
				do {
					System.out.println("Insira o telefone do pai: ");
					textos = scan.nextLine();				
					
				} while (validacao.validarNumero(textos) == false);
				
				textos = validacao.infoFiltrada();
				infoAluno.setTelPai(textos);
				contador++;

			case 10:				
				do {
					System.out.println("Insira o telefone da mãe: ");
					textos = scan.nextLine();
					
				} while (validacao.validarNumero(textos) == false);

				textos = validacao.infoFiltrada();
				infoAluno.setTelMae(textos);
				contador++;

			case 11:
				System.out.println("Insira a idade: ");
				textos = scan.nextLine();
				infoAluno.setIdade(textos);
				contador++;

			default:
				arquivo.salvarDados(infoAluno);
				System.out.println("Cadastro finalizado!");
				System.out.println(System.getProperty("Arquivo salvo em: " + "user.dir"));
				contador = 1;
				menuPrincipal();
				break;

			}
			
		} catch (java.util.InputMismatchException e) {
			System.out.println("INSIRA APENAS NUMEROS!");
			scan.next(); // limpar a linha para não ocorrer loop
			menuCadastro();

		}
		
	}
	
	//TODO:adicionar metodo dedicado para sair de alguma função
	//verArqMenu
	public void menuVisualizar() {
		limparTela();
		scan.nextLine();
		System.out.println("Arquivos existentes \n==========");
		arquivo.lerPasta();
		System.out.println("========== \nSelecione o nome do arquivo que deseja ler(NÃO ADICIONE A EXTENSÃO): ");
		textos = scan.nextLine();
		arquivo.lerArquivo(textos);

	}
	
	//deletarArqMenu
	public void menuDeletar() {
		limparTela();
		scan.nextLine();
		System.out.println("==========");
		arquivo.lerPasta();
		System.out.println("==========");
		System.out.println("Qual arquivo deseja deletar?(NÃO ADICIONE A EXTENSÃO) ");
		textos = scan.nextLine();
		arquivo.apagarArquivo(textos);
		
	}
	
	//editArqMenu
	public void menuEditar() {
		scan.nextLine();
		limparTela();
		System.out.println("==========");
		arquivo.lerPasta();
		System.out.println("==========");
		System.out.println("Qual arquivo deseja editar?");
		textos = scan.nextLine();
		arquivo.editarArquivo(textos);
		
	}
	
	//gambiarra pura, mas eficiente
	public void limparTela() {
		for (int i = 0; i < 50; i++) {
			System.out.println(" ");
			
		}
		
	}

}
