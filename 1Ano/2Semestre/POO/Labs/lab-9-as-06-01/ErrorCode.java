public enum ErrorCode {
    MEMBER_DOES_NOT_EXISTS,
    MEMBER_ALREADY_EXISTS,
    LOGIN_NAME_CANT_BE_NULL,
    LOGIN_NAME_MUST_BE_BIGGER,
    MEMBER_NIF_CANT_BE_NULL,
    MEMBER_NIF_IS_INCORRECT,
    SHOPPING_CART_IS_EMPTY,
    NUMBER_OF_BOXES_MUST_BE_POSITIVE,
    FILE_CANT_BE_NULL_OR_EMPTY;

    public String toString(){
        switch (this){
            case MEMBER_DOES_NOT_EXISTS : return "O utilizador não se encontra registado";
            case MEMBER_ALREADY_EXISTS : return "O nome de login já se encontra em utilização";
            case LOGIN_NAME_CANT_BE_NULL : return "O nome do utilizador tem de ser fornecido";
            case LOGIN_NAME_MUST_BE_BIGGER : return "O nome do utilizador tem de ser composto por pelo menos 5 caracteres";
            case MEMBER_NIF_CANT_BE_NULL : return "O NIF do utilizador tem de ser fornecido";
            case MEMBER_NIF_IS_INCORRECT : return "O NIF do utilizador está incorreto";
            case SHOPPING_CART_IS_EMPTY : return "O carrinho de compras está vazio";
            case NUMBER_OF_BOXES_MUST_BE_POSITIVE : return "O numero de caixas têm de ser positivo";
            case FILE_CANT_BE_NULL_OR_EMPTY : return "O ficheiro para imprimir não pode ser vazio ou nulo";
            default: return "";
        }
    }
}
