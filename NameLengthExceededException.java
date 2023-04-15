package MyProject;
/** 사용자 정의 Exception으로, 이름이 너무 길면 오류가 발생한다.
	 * */
public class NameLengthExceededException extends Exception {
	
    public NameLengthExceededException() {
        super("NameLengthExceededException.");
    }
    public NameLengthExceededException(String msg) {
		super(msg);
	}
    
}