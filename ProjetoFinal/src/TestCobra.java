import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestCobra {

        private Cobra cobra;
        private Comida comida;

        // CONSTRUTOR DA COBRA
        // -------------------------------------------------------------------

        @Before
        public void setUp() {
                // Inicializa a cobra com a dimensão e ponto inicial
                cobra = new Cobra(5, new Ponto(10, 10));
                comida = new Comida("quadrado", new Ponto(15, 10), 2);  
        }

        @Test
        public void testInicializacaoCobra() {
                assertNotNull("A cobra não deveria ser nula", cobra);
                assertNotNull("A lista de quadrados não deveria ser nula", cobra.getCobra());
                assertFalse("A lista de quadrados não deveria estar vazia", cobra.getCobra().isEmpty());
                // assertEquals("A cobra deve ter exatamente um quadrado", 3,
                // cobra.getCobra().size());

                // Verifica se a cabeça da cobra está na posição correta
                Quadrado cabeca = cobra.getCobra().get(0);
                // Supondo que você tem um método para obter o centro do Quadrado
                Ponto centroEsperado = new Ponto(10, 10);
                assertEquals("A cabeça da cobra deve estar no ponto (10, 10)", centroEsperado, cabeca.findCentroide());
        }

        // MOVIMENTO DA COBRA
        // -------------------------------------------------------------------

        @Test // Este teste falhará pois a Cobra tem como direcao inicial UP
        public void testMoverCobraDirecaoDown() {
                Ponto oldHeadPos = new Ponto(cobra.getCobra().get(0).findCentroide().getX(),
                                cobra.getCobra().get(0).findCentroide().getY());

                cobra.direcionarCobra(Direcao.DOWN);
                Ponto newHeadPos = new Ponto(cobra.getCobra().get(0).findCentroide().getX(),
                                cobra.getCobra().get(0).findCentroide().getY());

                assertEquals(oldHeadPos.getX(), newHeadPos.getX(),
                                "A cabeça da cobra deve permanecer na mesma posição X.");
                assertNotEquals(oldHeadPos.getY() + cobra.getDimensao(), newHeadPos.getY(),
                                "A cabeça da cobra deve mover-se para baixo pelo tamanho da dimensão.");

                // Testar se o corpo segue corretamente
                assertEquals(oldHeadPos.getX(), cobra.getCobra().get(1).findCentroide().getX(),
                                "O segundo segmento deve seguir a cabeça na coordenada X.");
                assertNotEquals(oldHeadPos.getY(), cobra.getCobra().get(1).findCentroide().getY(),
                                "O segundo segmento deve seguir a cabeça na coordenada Y.");
        }

        @Test
        public void testMoverCobraDirecaoUp() {
                Ponto oldHeadPos = new Ponto(cobra.getCobra().get(0).findCentroide().getX(),
                                cobra.getCobra().get(0).findCentroide().getY());

                cobra.direcionarCobra(Direcao.UP);
                Ponto newHeadPos = new Ponto(cobra.getCobra().get(0).findCentroide().getX(),
                                cobra.getCobra().get(0).findCentroide().getY());

                assertEquals(oldHeadPos.getX(), newHeadPos.getX(),
                                "A cabeça da cobra deve permanecer na mesma posição X.");
                assertEquals(oldHeadPos.getY() - cobra.getDimensao(), newHeadPos.getY(),
                                "A cabeça da cobra deve mover-se para cima pelo tamanho da dimensão.");

                // Testar se o corpo segue corretamente
                assertEquals(oldHeadPos.getX(), cobra.getCobra().get(1).findCentroide().getX(),
                                "O segundo segmento deve seguir a cabeça na coordenada X.");
                assertEquals(oldHeadPos.getY(), cobra.getCobra().get(1).findCentroide().getY(),
                                "O segundo segmento deve seguir a cabeça na coordenada Y.");
        }

        @Test
        public void testMoverCobraDirecaoLeft() {
                Ponto oldHeadPos = new Ponto(cobra.getCobra().get(0).findCentroide().getX(),
                                cobra.getCobra().get(0).findCentroide().getY());

                cobra.direcionarCobra(Direcao.LEFT);
                Ponto newHeadPos = new Ponto(cobra.getCobra().get(0).findCentroide().getX(),
                                cobra.getCobra().get(0).findCentroide().getY());

                assertEquals(oldHeadPos.getX() - cobra.getDimensao(), newHeadPos.getX(),
                                "A cabeça da cobra deve mover-se para a esquerda pelo tamanho da dimensão.");
                assertEquals(oldHeadPos.getY(), newHeadPos.getY(),
                                "A cabeça da cobra deve permanecer na mesma posição Y.");

                // Testar se o corpo segue corretamente
                assertEquals(oldHeadPos.getX(), cobra.getCobra().get(1).findCentroide().getX(),
                                "O segundo segmento deve seguir a cabeça na coordenada X.");
                assertEquals(oldHeadPos.getY(), cobra.getCobra().get(1).findCentroide().getY(),
                                "O segundo segmento deve seguir a cabeça na coordenada Y.");
        }

        @Test
        public void testMoverCobraDirecaoRight() {
                Ponto oldHeadPos = new Ponto(cobra.getCobra().get(0).findCentroide().getX(),
                                cobra.getCobra().get(0).findCentroide().getY());

                cobra.direcionarCobra(Direcao.RIGHT);
                Ponto newHeadPos = new Ponto(cobra.getCobra().get(0).findCentroide().getX(),
                                cobra.getCobra().get(0).findCentroide().getY());

                assertEquals(oldHeadPos.getX() + cobra.getDimensao(), newHeadPos.getX(),
                                "A cabeça da cobra deve mover-se para a direita pelo tamanho da dimensão.");
                assertEquals(oldHeadPos.getY(), newHeadPos.getY(),
                                "A cabeça da cobra deve permanecer na mesma posição Y.");

                // Testar se o corpo segue corretamente
                assertEquals(oldHeadPos.getX(), cobra.getCobra().get(1).findCentroide().getX(),
                                "O segundo segmento deve seguir a cabeça na coordenada X.");
                assertEquals(oldHeadPos.getY(), cobra.getCobra().get(1).findCentroide().getY(),
                                "O segundo segmento deve seguir a cabeça na coordenada Y.");
        }

        @Test
        public void testGetTamanho() {
                assertEquals("O tamanho da cobra deve ser igual ao número de quadrados na lista",
                                cobra.getCobra().size(), cobra.getTamanho());
        }

        @Test
        public void testDetectarColisaoComComida() {
        cobra.direcionarCobra(Direcao.RIGHT);  // Movimento para colidir com a comida
        assertTrue("A cobra deve detectar a colisão com a comida", cobra.getCabeca().containsPonto(comida.getPosicaoComida()));
        }

        @Test
        public void testAdicionarNovaParteCobra() {
                int tamanhoInicial = cobra.getTamanho();
                cobra.addNewSnakePart();
                assertEquals("O tamanho da cobra deve aumentar após adicionar uma nova parte", tamanhoInicial + 1,
                                cobra.getTamanho());
        }

        @Test
        public void testCrescimentoAposComer() {
        int tamanhoInicial = cobra.getTamanho();
        cobra.direcionarCobra(Direcao.DOWN);  // Supõe que a comida está logo abaixo da cabeça
        cobra.addNewSnakePart();  // Simula a ação de comer
        assertEquals("A cobra deve crescer após comer", tamanhoInicial + 1, cobra.getTamanho());
        }

        @Test
        public void testVerificarColisaoComCorpo() {
                assertFalse("A cobra não deve colidir consigo mesma inicialmente", cobra.verificarColisaoComCorpo());
        }

        @Test
        public void testGetCabeca() {
                Quadrado cabeca = cobra.getCabeca();
                assertNotNull("A cabeça da cobra não deveria ser nula", cabeca);
                assertEquals("A cabeça da cobra deve ser o primeiro quadrado da lista", cabeca,
                                cobra.getCobra().get(0));
        }

        @Test
        public void testMovimentoInvalidoDirecaoOposta() {
        cobra.direcionarCobra(Direcao.UP);  // Movimento inicial para cima
        cobra.direcionarCobra(Direcao.DOWN);  // Tentativa de mover para baixo imediatamente após
        Ponto posicaoCabecaAposMovimentos = cobra.getCabeca().findCentroide();
        assertEquals("A cobra não deve mover para baixo imediatamente após mover para cima", 5, posicaoCabecaAposMovimentos.getY(), 0.01);
        }
}
