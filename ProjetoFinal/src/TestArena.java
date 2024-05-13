import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestArena {

    private Arena arena;

    @Before
    public void setUp() {
        arena = new Arena(100, 100, 10, "quadrado", 5);
    }

    @Test
    public void testInicializacaoArena() {
        assertNotNull("A arena não deveria ser nula", arena);
        assertEquals("A largura da arena deve ser 100", 100, arena.getLargura());
        assertEquals("A altura da arena deve ser 100", 100, arena.getAltura());
        assertNotNull("A cobra não deveria ser nula", arena.getCobra());
        assertNotNull("A comida não deveria ser nula", arena.getComida());
        assertEquals("O tipo de comida deve ser 'quadrado'", "quadrado", arena.getTipoComida());
        assertEquals("A dimensão da comida deve ser 10", 10, arena.getDimensaoComida());
        assertEquals("A dimensão da cobra deve ser 5", 5, arena.getDimensaoCobra());
        assertTrue("O jogo deve estar ativo inicialmente", arena.isJogoAtivo());
        assertEquals("A pontuação inicial deve ser 0", 0, arena.getPontuacao());
    }

    @Test
    public void testGerarComida() {
        Comida comida = arena.gerarComida();
        assertNotNull("A comida gerada não deveria ser nula", comida);
        assertEquals("A comida deve ter o tipo especificado", "quadrado", comida.getTipoComida());
    }


    @Test
    public void testAtualizarArenaColisaoBorda() {
        arena.getCobra().moverCobra(0, -100, 0);
        assertFalse("O jogo deve terminar se a cobra colidir com a borda", arena.isJogoAtivo());
    }

    @Test
    public void testAtualizarArenaColisaoCobra() {
        arena.getCobra().moverCobra(0, 20, 20);
        arena.getCobra().moverCobra(1, 20, 30);
        arena.getCobra().moverCobra(2, 20, 40);
        assertFalse("O jogo deve terminar se a cobra colidir consigo mesma", arena.isJogoAtivo());
    }

    @Test
    public void testAtualizar() {
        arena.atualizar(Direcao.DOWN);
        assertTrue("O jogo deve continuar ativo após uma atualização válida", arena.isJogoAtivo());
    
    }

    @Test
    public void testGerarCobra() {
        Cobra cobra = arena.gerarCobra();
        assertNotNull("A cobra gerada não deveria ser nula", cobra);
        assertEquals("A cobra deve ter a dimensão especificada", 5, cobra.getDimensao(), 0);
        assertFalse("A cobra não deve estar dentro da comida inicialmente", cobra.getCobra().stream().anyMatch(segmento -> segmento.containsPonto(arena.getComida().getPosicaoComida())));
    }

    @Test
    public void testInicializarGrelha() {
        Quadrado[][] grelha = arena.getGrelha();
        assertNotNull("A grelha não deveria ser nula", grelha);
        assertEquals("A largura da grelha deve ser igual à largura da arena", arena.getLargura(), grelha.length);
        assertEquals("A altura da grelha deve ser igual à altura da arena", arena.getAltura(), grelha[0].length);
    }

}
