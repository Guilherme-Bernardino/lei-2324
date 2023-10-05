package products;

/**
 * EN
 * Enumerator for Product type identification.
 *
 * PT
 * Enumerador para identificação do tipo de produto.
 *
 * @author guilh
 */
public enum ProductType {
    ROUPA,
    ACESSORIO,
    BRINQUEDO_PEQUENO,
    BRINQUEDO_GRANDE,
    ELETRONICO_PEQUENO,
    ELETRONICO_GRANDE,
    LIVRO;

    @Override
    public String toString() {
        switch(this){
            case ROUPA: return "Roupa";
            case ACESSORIO: return "Acessorio";
            case BRINQUEDO_PEQUENO: return "Pequeno Brinquedo";
            case BRINQUEDO_GRANDE: return "Grande Brinquedo";
            case ELETRONICO_PEQUENO: return "Pequeno Equi. Eletronico";
            case ELETRONICO_GRANDE: return "Grande Equi. Eletronico";
            case LIVRO: return "Livro";
            default: return "N/A";
        }
    }
}
