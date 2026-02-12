package br.com.escola.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.com.escola.model.Aluno;

public class Dados {
	Scanner scan = new Scanner(System.in);
	String dirAtual = System.getProperty("user.dir");
	String textos;
	int numeros;
	FileWriter writer;
	Menu tela;
	Path arquivo;
	
	public boolean salvarDados(Aluno infoAluno){
		String nomePasta = infoAluno.getNome() + ".txt";
		Path caminho = Paths.get(nomePasta);
		int contador = 1;
		
		String dados = "NOME: " + infoAluno.getNome() + "\nRG: " + infoAluno.getRg() + "\nCPF: " + infoAluno.getCpf() + "\nENDEREÇO: " 
		+ infoAluno.getEndereco() + "\nNOME DO PAI: " + infoAluno.getPai() + "\nNOME DA MÃE: " + infoAluno.getMae() + "\nEMAIL DO PAI: " 
		+ infoAluno.getEmailPai() + "\nEMAIL DA MÃE: " + infoAluno.getEmailMae() + "\nTELEFONE DO PAI: " + infoAluno.getTelPai() + "\nTELEFONE DA MÃE: "
		+ infoAluno.getTelMae() + "\nIDADE DO ALUNO: " + infoAluno.getIdade();
		
		//todo processo de criação do arquivo, o que for escrito fica na variavel dados
		
		try {			
			if(Files.notExists(caminho)) {
				Files.createFile(caminho);
				
			}
			else {
				do {
					nomePasta = infoAluno.getNome() + contador + ".txt";
					caminho = Paths.get(nomePasta);
					contador++;
					System.out.println("teste contador");
				} while (Files.exists(caminho));
				
				Files.createFile(caminho);
				
			}
			//escrever no arquivo	
			Files.write(caminho, dados.getBytes());
			System.out.println("Dados salvos!");
			
		} catch (IOException e) {
			System.out.println("Erro ao criar o arquivo!");
			return false;
		}
		
		pause();
		return true;
	}
	
	public void lerPasta() {
		File pasta = new File("."); // o ponto representa a pasta atual
		File[] arquivos = pasta.listFiles();
		Boolean estadoPasta = false;
		
		//TODO:futuro bug, se a pasta estiver vazia nem executa
		for(File arquivo : arquivos) {
			if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
				System.out.println(arquivo.getName());
				estadoPasta = true;
			}
			
		}
		
		if (estadoPasta == false) {
			System.out.println("Não há registros na pasta");
		}
		
	}
	
	public void lerArquivo(String nome) {
		tela = new Menu();
		
		try {
			tela.limparTela();
			//TODO: fazer ele ler o arquivo usando um id que o usuario digitou
			BufferedReader leitorLinhas = new BufferedReader(new FileReader(nome + ".txt"));//qual arquivo vai ler
			String linha;//conteúdo de cada linha
			
			while ((linha = leitorLinhas.readLine()) != null) {
				System.out.println(linha);
				
			}
			
			leitorLinhas.close();
			pause();
			tela.menuPrincipal();
			
		} catch (IOException e) {
			tela.limparTela();
			System.out.println("Erro ao ler o arquivo!");
			tela.menuPrincipal();			
		}
		
	}
	
	public void apagarArquivo(String nome) {
		try {
			dirAtual = System.getProperty("user.dir");
			Path base = Paths.get(dirAtual);
			arquivo = base.resolve(nome + ".txt");
		
			if (Files.exists(arquivo)) {
				Files.delete(arquivo);
				System.out.println("Registro apagado com sucesso!");
			} else {
				System.out.println("Arquivo não encontrado");
				//apagarArquivo(nome);
			}
			
			System.out.println(arquivo);
			pause();
			
		} catch (IOException e) {
			System.out.println(arquivo);
			System.out.println("Erro ao apagar o arquivo");
			System.out.println("Verifique se o arquivo não está aberto!");
			
			pause();
			
		}
			
	}
	
	public void editarArquivo(String nome) {
		try {
			dirAtual = System.getProperty("user.dir");
			System.out.println(dirAtual);
			Path caminho = Paths.get(nome + ".txt");
			List<String> linhas = Files.readAllLines(caminho);
			Regras validacao = new Regras();
			
			/*String[] dadosLinha = {"NOME: ", "RG: ", "CPF: ", "ENDEREÇO: ", "NOME DO PAI: ", "NOME DA MÃE: ",
			"EMAIL DO PAI: ", "EMAIL DA MÃE: ", "TELEFONE DO PAI: ", "TELEFONE DA MÃE: ", "IDADE DO ALUNO: "};*/
					
			
			for (String linha : linhas) {
				System.out.println(linha);
			}
			
			System.out.println("Qual dado deseja alterar?\n1-Nome\n2-Rg\n3-Cpf\n4-Endereço\n5-Nome do pai"
			+ "\n6-Nome da mãe\n7-Email do pai\n8-Email da mãe\n9-Telefone do pai\n10-Telefone da mãe\n11-Idade do aluno");
	
			int opcao = scan.nextInt();
			String novoDado;
			scan.nextLine();
			
			for (int i = 0; i < linhas.size(); i++) {
				
				if (linhas.get(i).contains("NOME: ") && opcao == 1) {
					do {
						System.out.println("Insira o novo nome: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarNome(novoDado) ==  false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "NOME: " + novoDado);
				}
				else if (linhas.get(i).contains("RG: ") && opcao == 2) {
					do {
						System.out.println("Insira o novo rg: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarRg(novoDado) == false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "RG: " + novoDado);
				}
				else if (linhas.get(i).contains("CPF: ") && opcao == 3) {
					do {
						System.out.println("Insira o novo cpf: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarCpf(novoDado) == false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "CPF: " + novoDado);
				}
				else if (linhas.get(i).contains("ENDEREÇO: ") && opcao == 4) {					
					do {
						System.out.println("Insira o novo endereço: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarEndereco(novoDado) == false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "ENDEREÇO: " + novoDado);
				}
				else if (linhas.get(i).contains("NOME DO PAI: ") && opcao == 5) {					
					do {
						System.out.println("Insira o novo nome do pai: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarNome(novoDado) == false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "NOME DO PAI: " + novoDado);
				}
				else if (linhas.get(i).contains("NOME DA MÃE: ") && opcao == 6) {
					do {
						System.out.println("Insira o novo nome da mãe: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarNome(novoDado) == false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "NOME DA MÃE: " + novoDado);
				}
				else if (linhas.get(i).contains("EMAIL DO PAI: ") && opcao == 7) {					
					do {
						System.out.println("Insira o novo email do pai: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarEmail(novoDado) == false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "EMAIL DO PAI: " + novoDado);
				}
				else if (linhas.get(i).contains("EMAIL DA MÃE: ") && opcao == 8) {					
					do {
						System.out.println("Insira o novo email da mãe: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarEmail(novoDado) == false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "EMAIL DA MÃE: " + novoDado);
				}
				else if (linhas.get(i).contains("TELEFONE DO PAI: ") && opcao == 9) {					
					do {
						System.out.println("Insira o novo telefone do pai: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarNumero(novoDado) == false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "TELEFONE DO PAI: " + novoDado);
				}
				else if (linhas.get(i).contains("TELEFONE DA MÃE: ") && opcao == 10) {					
					do {
						System.out.println("Insira o novo telefone da mãe: ");
						novoDado = scan.nextLine();
						
					} while (validacao.validarNumero(novoDado) == false);
					
					novoDado = validacao.infoFiltrada();
					linhas.set(i, "TELEFONE DA MÃE: " + novoDado);
				}
				else if (linhas.get(i).contains("IDADE DO ALUNO: ") && opcao == 11) {
					System.out.println("Insira a nova idade do aluno: ");
					novoDado = scan.nextLine();
					linhas.set(i, novoDado);
				}
				
			}
			
			Files.write(caminho, linhas);
			
		} catch (IOException e) {
			System.out.println("Erro ao editar");
		}
		
		pause();

	}
	
	public void pause() {
		tela = new Menu();
		System.out.println("Aperte qualquer tecla para prosseguir");
		scan.nextLine();
		tela.limparTela();
		tela.menuPrincipal();
		
	}
		
}
