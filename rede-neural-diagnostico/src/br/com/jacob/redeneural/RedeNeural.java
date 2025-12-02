package br.com.jacob.redeneural;

import java.util.Random;

@SuppressWarnings("unused")
public class RedeNeural {

    private double[][] pesos;

    public RedeNeural(int qtdNeuronios, int tamanhoVetorEntrada){

        this.pesos = new double[qtdNeuronios][tamanhoVetorEntrada];

        inicializarSinapsesAleatoriamente(pesos);

        //inicializarSinapsesComoProfessor(pesos);
        
    }

    public void treinarNeuronios(double[][] entradas, double[][] saidaDesejadas, double erroAceitavel){
        
        double erroEpoca = 1;
        double erroMedioAmostra;
        int epocas = 0;

        System.out.printf("iniciando treinamento com %d amostras\n", entradas.length);

        while (erroEpoca >= erroAceitavel) {

            double somaErrosQuadraticosEpocas = 0;

            for (int i = 0; i < entradas.length; i++){

                double[] x = entradas[i];

                for (int j = 0; j < pesos.length; j++){

                    //passo 2: v = X.W
                    double v = treinarVetorEntrada(pesos[j], x);

                    //passo 3: calcular Y
                    double y = calcularSaidaY(v);

                    //passo 4: calcular erro, e = yd - y
                    double erro = calcularErro(saidaDesejadas[i][j], y);

                    //passo 5: atualiza os pesos
                    pesos[j] = atualizarSinapses(erro, x, pesos[j]);

                    //passo 6: acumula os pesos na variavel
                    somaErrosQuadraticosEpocas += Math.pow(erro, 2);

                }

                //continua o passo 6: Ei = (e1² + e2² ... + en²) / n
                erroMedioAmostra = somaErrosQuadraticosEpocas / pesos.length;
                somaErrosQuadraticosEpocas += erroMedioAmostra;

            }

            //Eepoca = (E1² + E2² ... + En²) / n
            erroEpoca = somaErrosQuadraticosEpocas / entradas.length;    
            epocas++;

            System.out.printf("época %d, taxa de erro de %.5f%n", epocas, erroEpoca);
        }

        System.out.println("treinamento finalizado\n");
    }

    public void classificar(double[] entrada) {

        String[] nomesDoencas = {"Gripe", "Dengue", "Catapora"};
        
        System.out.println("diagnostico para a entrada informada");
        
        //percorre pelos neuronios
        for (int i = 0; i < pesos.length; i++) {
            
            //calcula v usando o pesos ja treinados
            double v = treinarVetorEntrada(pesos[i], entrada);
            
            double y = calcularSaidaY(v);
            
            String resultado = (y == 1) ? "positivo" : "negativo";
            
            System.out.println("->" + nomesDoencas[i] + ": " + resultado);
        }
        System.out.println();
    }

    //passo 1
    private void inicializarSinapsesAleatoriamente(double[][] pesos){
        Random rnd = new Random();
        
        for (int i = 0; i < pesos.length; i++) {
            for (int j = 0; j < pesos[i].length; j++) {
                int valorAleatorio = rnd.nextInt(-1, 2);
                
                while (valorAleatorio == 0) {
                    valorAleatorio = rnd.nextInt(-1, 2);
                }
                
                pesos[i][j] = valorAleatorio;
            }
        }   
    }

    private void inicializarSinapsesComoProfessor(double[][] pesos){

        for (int i = 0; i < pesos.length; i++){
            pesos[0][i] = 1;
            pesos[1][i] = -1;
            pesos[2][i] = i % 2 == 0 ? 1 : -1;
        }
    }

    //passo 2
    private double treinarVetorEntrada(double[] vetorNeuronio, double[] vetorEntrada){
        
        double somatorioVetor = 0;

        if (vetorNeuronio.length != vetorEntrada.length){
            throw new RuntimeException("Os tamanhos dos vetores devem ser iguais");
        } 

        for (int i = 0; i < vetorNeuronio.length; i++){
            somatorioVetor += vetorNeuronio[i] * vetorEntrada[i];
        }
        
        return somatorioVetor;
    }

    //passo 3
    private double calcularSaidaY(double v){
        return v > 0 ? 1 : -1;
    }

    //passo 4
    private double calcularErro(double valorDesejado, double saida){
        return valorDesejado - saida;
    }

    //passo 5
    private double[] atualizarSinapses(double erro, double[] x, double[] w){
        //TODO tentar fazer o gamma alterar entre 0.9 e 0.2

        final double GAMMA = 0.5;

        if (erro == 0) return w;

        double[] deltaW = new double[w.length];

        // calcula o DELTA(W)
        for (int i = 0; i < deltaW.length; i++){
            deltaW[i] = GAMMA * erro * x[i];
        }

        for (int i = 0; i < w.length; i++){
            w[i] += deltaW[i];
        }

        return w;
    }

}
