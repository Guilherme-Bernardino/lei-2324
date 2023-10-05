public class CoffeeShopIllegalArgumentException extends IllegalArgumentException{

    private ErrorCode errorCode;

    public CoffeeShopIllegalArgumentException(ErrorCode code){
        this.errorCode = code;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }

}
