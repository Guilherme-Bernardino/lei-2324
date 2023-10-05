/**
 * Representa os tipos de membros e descontos.
 *
 * @author POO 2022/2023
 */
public enum MemberType{
    AMATEUR, CONNOISSEUR, AMBASSADOR, OTHER;

    /**
     * Other - Preço total 
     * Amateur - 5% desconto 
     * Connoisseur - 10% desconto
     * Ambassador - 25% desconto
     *
     * @return
     */
    public float getDiscount() {
        switch (this) {
            case AMATEUR:
                return 5.0f;
            case CONNOISSEUR:
                return 10.0f;
            case AMBASSADOR:
                return 25.0f;
            default:
                return 0.0f;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case AMATEUR:
                return "Amador";
            case CONNOISSEUR:
                return "Conhecedor";
            case AMBASSADOR:
                return "Embaixador";
            case OTHER:
                return "Desconhecido";
        }
        return "";
    }
}
