/** Testes do algoritmo fixo e dos passos especializados por cada bebida. */
public final class TemplateMethodTest {
    private static int verificacoes;
    private static void verificar(boolean condicao, String mensagem) {
        if (!condicao) throw new AssertionError(mensagem);
        verificacoes++;
    }
    private static void contem(String texto, String trecho, String mensagem) {
        verificar(texto.contains(trecho), mensagem);
    }
    private static void naoContem(String texto, String trecho, String mensagem) {
        verificar(!texto.contains(trecho), mensagem);
    }
    public static void main(String[] args) {
        String cafe = new Main.Cafe().preparar();
        contem(cafe, "Fervendo água.", "Café ferve a água");
        contem(cafe, "Passando o café.", "Café executa seu passo específico");
        contem(cafe, "Despejando na xícara.", "Café mantém etapa de servir");
        contem(cafe, "Adicionando açúcar e leite.", "Café adiciona seu complemento");
        verificar(cafe.indexOf("Fervendo") < cafe.indexOf("Passando"), "Ferver ocorre antes de preparar o café");
        verificar(cafe.indexOf("Passando") < cafe.indexOf("Despejando"), "Preparar ocorre antes de servir");
        verificar(cafe.indexOf("Despejando") < cafe.indexOf("Adicionando"), "Complemento ocorre depois de servir");

        String chaComLimao = new Main.Cha(true).preparar();
        contem(chaComLimao, "Infusionando o chá.", "Chá executa seu passo específico");
        contem(chaComLimao, "Adicionando limão.", "Chá com limão inclui complemento");
        String chaSemLimao = new Main.Cha(false).preparar();
        contem(chaSemLimao, "Fervendo água.", "Chá sem limão também ferve a água");
        contem(chaSemLimao, "Despejando na xícara.", "Chá sem limão também é servido");
        naoContem(chaSemLimao, "Adicionando limão.", "Passo opcional pode ser omitido");
        verificar(chaComLimao.indexOf("Infusionando") < chaComLimao.indexOf("Despejando"), "Ordem fixa mantida no chá");
        verificar(!cafe.equals(chaComLimao), "Subclasses produzem preparos diferentes");
        System.out.println(verificacoes + " verificações aprovadas.");
    }
}
