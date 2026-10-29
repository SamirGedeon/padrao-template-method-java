import java.util.Objects;

/** Demonstra Template Method com preparo de bebidas quentes. */
public class Main {
    abstract static class BebidaQuente {
        /** Define a sequência fixa do preparo; subclasses implementam os passos variáveis. */
        public final String preparar() {
            StringBuilder resultado = new StringBuilder();
            resultado.append("Fervendo água.\n");
            resultado.append(prepararIngrediente()).append('\n');
            resultado.append("Despejando na xícara.\n");
            if (querAdicionarComplemento()) {
                resultado.append(adicionarComplemento()).append('\n');
            }
            return resultado.toString();
        }
        protected abstract String prepararIngrediente();
        protected boolean querAdicionarComplemento() { return true; }
        protected abstract String adicionarComplemento();
    }

    static final class Cafe extends BebidaQuente {
        protected String prepararIngrediente() { return "Passando o café."; }
        protected String adicionarComplemento() { return "Adicionando açúcar e leite."; }
    }

    static final class Cha extends BebidaQuente {
        private final boolean comLimao;
        Cha(boolean comLimao) { this.comLimao = comLimao; }
        protected String prepararIngrediente() { return "Infusionando o chá."; }
        protected boolean querAdicionarComplemento() { return comLimao; }
        protected String adicionarComplemento() { return "Adicionando limão."; }
    }

    public static void main(String[] args) {
        BebidaQuente cafe = new Cafe();
        BebidaQuente cha = new Cha(true);
        System.out.println("=== Café ===");
        System.out.print(cafe.preparar());
        System.out.println("=== Chá ===");
        System.out.print(cha.preparar());
    }
}
