package br.com.jacob.redeneural;

public class Main{
    @SuppressWarnings("unused")
    public static void main(String[] args){

        /*
        sintomas, os indices do vetor representa cada sintoma nessa ordem
        1° febre
        2° coriza
        3° dor de cabeça
        4° doe de garganta
        5° manchas no corpo
        6° dor no corpo
        7° bolhas no corpo
        */

        //vetor de diagnosticos
        double[] vetorGripe = {1, 1, 1, 1, -1, -1 ,-1};
        double[] vetorDengue = {1, -1, -1, -1, 1, 1, -1};
        double[] vetorCatapora = {1, -1, -1, -1, -1, 1, 1};

        /* OBS 1 - criar mais uma amostra pra cada doenca */

        //conjunto de treino
        double[][] entradas = {
            //gripe
            { 1,   1,  1,  1,  1, -1, -1, -1 }, //gripe normal
            { 1,   1,  1, -1,  1, -1,  1, -1 }, //gripe sem dor de cabeça, mas com dor no corpo

            //dengue
            { 1,   1, -1, -1, -1,  1,  1, -1 }, //dengue normal
            { 1,   1, -1,  1, -1,  1,  1, -1 }, //dengue mais dor de cabeça

            //catapora
            { 1,   1, -1, -1, -1, -1,  1,  1 }, //catapora normal
            { 1,  -1, -1, -1, -1, -1,  1,  1 }  //catapora sem febre mas com bolhas
        };

        /*
        valor desejado pra cada vetor (1 pra positivo e -1 pra negativo)
        1° e 2° pra gripe
        3° e 4° pra dengue
        5° e 6° pra catapora
        */
        double[][] valoresDesejado = {
            {  1, -1, -1 },
            {  1, -1, -1 },
            
            { -1,  1, -1 },
            { -1,  1, -1 },
            
            { -1, -1,  1 },
            { -1, -1,  1 } 
        };

        /* OBS 2 - treinar ate 0.001 */

        RedeNeural redeNeural = new RedeNeural(3, 8);
        redeNeural.treinarNeuronios(entradas, valoresDesejado, 0.001);

        /* OBS3 - testar a rede com os ultimos vetores */

        double[] pacienteAleatorio1 = {1, -1, -1, -1, -1, 1, -1, 1};
        double[] pacienteAleatorio2 = {1, 1, 1, 1, 1, 1, 1, 1}; //praticamente um defunto

        redeNeural.classificar(pacienteAleatorio1);
        redeNeural.classificar(pacienteAleatorio2);
        redeNeural.classificar(entradas[0]);
        
    }
}