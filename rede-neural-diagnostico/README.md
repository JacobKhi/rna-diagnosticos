# Rede Neural Artificial - Diagnóstico de Doenças

Este repositório contém uma implementação de uma Rede Neural Artificial desenvolvida puramente em Java, sem a utilização de frameworks externos de Machine Learning.

O objetivo do projeto é demonstrar a lógica matemática e algorítmica por trás do aprendizado de máquina supervisionado, aplicando-o em um cenário de diagnóstico médico simplificado.

## Visão Geral

O sistema simula o diagnóstico de três patologias (Gripe, Dengue e Catapora) baseando-se em um vetor de entrada de sintomas. A rede é treinada para reconhecer padrões binários (presença ou ausência de sintomas) e classificar o resultado corretamente.

### Detalhes da Implementação

A arquitetura segue o modelo de rede de camada única (Single-Layer), implementando manualmente os seguintes conceitos:

* **Inicialização de Pesos:** Distribuição aleatória inicial das sinapses.
* **Processamento (Forward Pass):** Cálculo do produto escalar entre vetores de entrada e pesos.
* **Treinamento:** Utilização da regra delta para ajuste de pesos e minimização do erro quadrático médio ao longo de várias épocas.
* **Classificação:** Função de ativação degrau bipolar para determinar o resultado (positivo/negativo).

## Estrutura do Projeto

* **src/br/com/jacob/redeneural/RedeNeural.java**: Contém toda a lógica da rede, incluindo os métodos de treinamento e classificação.
* **src/br/com/jacob/redeneural/Main.java**: Define o dataset de treinamento e executa os testes de validação.

## Dados

O vetor de entrada considera 7 sintomas principais e o viés:
1. Febre
2. Coriza
3. Dor de cabeça
4. Dor de garganta
5. Manchas no corpo
6. Dor no corpo
7. Bolhas no corpo

## Como Executar

Para compilar e rodar o projeto, certifique-se de ter o JDK instalado.

1. Compile os arquivos gerando os binários na pasta `bin`:
   ```bash
   javac -d bin src/br/com/jacob/redeneural/*.java

2. Execute a classe principal informando o pacote completo:
   ```bash
   java -cp bin br.com.jacob.redeneural.Main

## Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.